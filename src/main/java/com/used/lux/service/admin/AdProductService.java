package com.used.lux.service.admin;

import com.used.lux.component.FileHandler;
import com.used.lux.domain.*;
import com.used.lux.dto.*;
import com.used.lux.dto.admin.AdProductDto;
import com.used.lux.repository.*;
import com.used.lux.request.ProductCreateRequest;
import com.used.lux.request.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    // 상품 리스트 조회
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

    // 상품 세부사항
    @Transactional(readOnly = true)
    public AdProductDto getProductDetail(Long productId) {
        // 상품 상세
        ProductDto productDto = ProductDto.from(productRepository.findById(productId).get());
        // 수정 로그(수정 전)
        List<ProductLogDto> productLogDtos = productLogRepository.findByProductIdOrderByCreatedAtDesc(productId)
                .stream().map(ProductLogDto::from).collect(Collectors.toCollection(ArrayList::new));
        // 주문내역
        List<ProductOrderLogDto> productOrderLogDtos = productOrderLogRepository.findByProductId(productId)
                .stream().map(ProductOrderLogDto::from).collect(Collectors.toCollection(ArrayList::new));
        // 경매내역
        List<AuctionLogDto> auctionLogDtos = auctionLogRepository.findByProductId(productId)
                .stream().map(AuctionLogDto::from).collect(Collectors.toCollection(ArrayList::new));
        return AdProductDto.of(productDto, productLogDtos, productOrderLogDtos, auctionLogDtos);
    }

    // 카테고리 리스트 조회
    @Transactional(readOnly = true)
    public List<CategoryBDto> getCategoryList() {
        return categoryBRepository.findAll()
                .stream().map(CategoryBDto::from).collect(Collectors.toCollection(ArrayList::new));
    }

    // 브랜드 리스트 조회
    @Transactional(readOnly = true)
    public List<BrandDto> getBrandList() {
        return brandRepository.findAll()
                .stream().map(BrandDto::from).collect(Collectors.toCollection(ArrayList::new));
    }

    // Admin 상품 등록
    @Transactional
    public void productCreate(ProductCreateRequest request) throws Exception {
        Product product = productRepository.findById(request.productId()).get();
        Brand brand = brandRepository.findById(request.brandId()).get();
        State state = stateRepository.findByStateStep("판매대기");
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

        List<Image> imageList = fileHandler.parseFileInfo(request, productResult); // 파일 처리
        // 파일이 존재할 때에만 처리
        if(!imageList.isEmpty()) {
            for(Image image : imageList) {
                // 파일을 DB에 저장
                System.out.println(image);
                imageRepository.save(image); // 이미지 이름 및 경로 DB에 저장
            }
        }
    }

    // Admin 상품 업데이트
    @Transactional
    public void productUpdate(Long productId, ProductUpdateRequest productUpdateRequest){
        // 업데이트에 필요한 entity 가져오기
        CategoryB categoryB = categoryBRepository.findById(productUpdateRequest.categoryBId()).get();
        CategoryM categoryM = categoryMRepository.findById(productUpdateRequest.categoryMId()).get();
        Brand brand = brandRepository.findById(productUpdateRequest.brandId()).get();
        Product product= productRepository.getReferenceById(productId);
        State state = stateRepository.findByStateStep(productUpdateRequest.stateStep());

        productLogRepository.save(ProductLog.of(null, productId,productUpdateRequest.productName(),state,categoryB,categoryM,productUpdateRequest.productPrice(), productUpdateRequest.productSellType()));
        // 내용 업데이트
        product.getAppraisal().getAppraisalRequest().setAppraisalProductName(productUpdateRequest.productName());
        product.setProductContent(productUpdateRequest.content());
        product.getAppraisal().getAppraisalRequest().setAppraisalBrand(brand);
        product.setCategoryB(categoryB);
        product.setCategoryM(categoryM);
        product.setProductSellType(productUpdateRequest.productSellType());
        product.setState(state);
        product.setProductPrice(productUpdateRequest.productPrice());

        // 레포지토리 저장
        Product result = productRepository.save(product);

        if (result.getProductSellType().equals("경매")) {
            Auction auction = auctionRepository.findByProductId(result.getId());
            if (auction == null) {
                State auctionState = stateRepository.findByStateStep("경매전");
                auctionRepository.save(Auction.of(result, auctionState, 0, result.getProductPrice(), 0,
                        null, null, 0, null));
            }
        } else {
            auctionRepository.deleteByProductId(result.getId());
        }
        System.out.println("모든 로직 정상 수행");
    }

}
