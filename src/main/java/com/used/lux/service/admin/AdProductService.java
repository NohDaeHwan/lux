package com.used.lux.service.admin;

import com.used.lux.component.FileHandler;
import com.used.lux.domain.*;
import com.used.lux.domain.auction.Auction;
import com.used.lux.domain.product.Image;
import com.used.lux.domain.product.Product;
import com.used.lux.domain.product.ProductLog;
import com.used.lux.dto.*;
import com.used.lux.dto.admin.AdProductDto;
import com.used.lux.dto.user.auction.AuctionLogDto;
import com.used.lux.dto.user.order.ProductOrderLogDto;
import com.used.lux.dto.user.product.ProductDto;
import com.used.lux.dto.user.product.ProductLogDto;
import com.used.lux.repository.*;
import com.used.lux.repository.appraisal.AppraisalRequestRepository;
import com.used.lux.repository.auction.AuctionLogRepository;
import com.used.lux.repository.auction.AuctionRepository;
import com.used.lux.repository.order.ProductOrderLogRepository;
import com.used.lux.repository.product.ImageRepository;
import com.used.lux.repository.product.ProductLogRepository;
import com.used.lux.repository.product.ProductRepository;
import com.used.lux.request.product.ProductCreateRequest;
import com.used.lux.request.product.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdProductService {

    private final ProductRepository productRepository;

    private final ProductLogRepository productLogRepository;

    private final ProductOrderLogRepository productOrderLogRepository;

    private final AuctionLogRepository auctionLogRepository;

    private final AppraisalRequestRepository appraisalRepository;

    private final CategoryBRepository categoryBRepository;

    private final CategoryMRepository categoryMRepository;

    private final BrandRepository brandRepository;

    private final FileHandler fileHandler;

    private final ImageRepository imageRepository;

    private final StateRepository stateRepository;

    private final AuctionRepository auctionRepository;

    // ?????? ????????? ??????
    @Transactional(readOnly = true)
    public Page<ProductDto> getProductList(String productSellType, String productBrand, String productGender,
                                           String productSize, String productGrade, String productState,
                                           String productDate, String query, Pageable pageable) {
        String[] dateResult = productDate.split("-");
        LocalDateTime date = LocalDateTime.of(Integer.parseInt(dateResult[0]),
                Integer.parseInt(dateResult[1]), Integer.parseInt(dateResult[2]), 00, 00);
        return productRepository.findByBackProductList(productSellType, productBrand, productGender, productSize,
                productGrade, productState, date, query, pageable).map(ProductDto::from);
    }

    // ?????? ????????????
    @Transactional(readOnly = true)
    public AdProductDto getProductDetail(Long productId) {
        // ?????? ??????
        ProductDto productDto = ProductDto.from(productRepository.findById(productId).get());
        // ?????? ??????(?????? ???)
        List<ProductLogDto> productLogDtos = productLogRepository.findByProductIdOrderByCreatedAtDesc(productId)
                .stream().map(ProductLogDto::from).collect(Collectors.toCollection(ArrayList::new));
        // ????????????
        List<ProductOrderLogDto> productOrderLogDtos = productOrderLogRepository.findByProductId(productId)
                .stream().map(ProductOrderLogDto::from).collect(Collectors.toCollection(ArrayList::new));
        // ????????????
        List<AuctionLogDto> auctionLogDtos = auctionLogRepository.findByProductId(productId)
                .stream().map(AuctionLogDto::from).collect(Collectors.toCollection(ArrayList::new));
        return AdProductDto.of(productDto, productLogDtos, productOrderLogDtos, auctionLogDtos);
    }

    // ???????????? ????????? ??????
    @Transactional(readOnly = true)
    public List<CategoryBDto> getCategoryList() {
        return categoryBRepository.findAll()
                .stream().map(CategoryBDto::from).collect(Collectors.toCollection(ArrayList::new));
    }

    // ????????? ????????? ??????
    @Transactional(readOnly = true)
    public List<BrandDto> getBrandList() {
        return brandRepository.findAll()
                .stream().map(BrandDto::from).collect(Collectors.toCollection(ArrayList::new));
    }

    // Admin ?????? ??????
    @Transactional
    public void productCreate(ProductCreateRequest request) throws Exception {
        Product product = productRepository.findById(request.productId()).get();
        Brand brand = brandRepository.findById(request.brandId()).get();
        State state = stateRepository.findByStateStep("????????????");
        CategoryB categoryB = categoryBRepository.findById(request.categoryBId()).get();
        CategoryM categoryM = categoryMRepository.findById(request.categoryMId()).get();

        product.getAppraisal().getAppraisalRequest().setAppraisalProductName(request.productName());
        product.setProductPrice(request.productPrice());
        product.setProductSellType(request.productSellType());
        product.getAppraisal().getAppraisalRequest().setAppraisalBrand(brand);
        product.setState(state);
        product.setCategoryB(categoryB);
        product.setCategoryM(categoryM);
        product.setProductContent(request.productContent());
        Product productResult = productRepository.save(product);

        List<Image> imageList = fileHandler.parseFileInfo(request, productResult); // ?????? ??????
        // ????????? ????????? ????????? ??????
        if(!imageList.isEmpty()) {
            for(Image image : imageList) {
                // ????????? DB??? ??????
                System.out.println(image);
                imageRepository.save(image); // ????????? ?????? ??? ?????? DB??? ??????
            }
        }
    }

    // Admin ?????? ????????????
    @Transactional
    public void productUpdate(Long productId, ProductUpdateRequest productUpdateRequest){
        // ??????????????? ????????? entity ????????????
        CategoryB categoryB = categoryBRepository.findById(productUpdateRequest.categoryBId()).get();
        CategoryM categoryM = categoryMRepository.findById(productUpdateRequest.categoryMId()).get();
        Brand brand = brandRepository.findById(productUpdateRequest.brandId()).get();
        Product product= productRepository.getReferenceById(productId);
        State state = stateRepository.findByStateStep(productUpdateRequest.stateStep());

        productLogRepository.save(ProductLog.of(null, productId,productUpdateRequest.productName(),state,categoryB,categoryM,productUpdateRequest.productPrice(), productUpdateRequest.productSellType()));
        // ?????? ????????????
        product.getAppraisal().getAppraisalRequest().setAppraisalProductName(productUpdateRequest.productName());
        product.setProductContent(productUpdateRequest.content());
        product.getAppraisal().getAppraisalRequest().setAppraisalBrand(brand);
        product.setCategoryB(categoryB);
        product.setCategoryM(categoryM);
        product.setProductSellType(productUpdateRequest.productSellType());
        product.setState(state);
        product.setProductPrice(productUpdateRequest.productPrice());

        // ??????????????? ??????
        Product result = productRepository.save(product);

        if (result.getProductSellType().equals("??????")) {
            Auction auction = auctionRepository.findByProductId(result.getId());
            if (auction == null) {
                State auctionState = stateRepository.findByStateStep("?????????");
                auctionRepository.save(Auction.of(result, auctionState, 0, result.getProductPrice(), 0,
                        null, null, 0, null));
            }
        } else {
            auctionRepository.deleteByProductId(result.getId());
        }
        System.out.println("?????? ?????? ?????? ??????");
    }

}
