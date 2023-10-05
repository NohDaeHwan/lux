package com.used.lux.service.admin;

import com.used.lux.component.FileHandler;
import com.used.lux.domain.*;
import com.used.lux.domain.appraisal.Appraisal;
import com.used.lux.domain.auction.Auction;
import com.used.lux.domain.constant.*;
import com.used.lux.domain.product.Image;
import com.used.lux.domain.product.Product;
import com.used.lux.domain.product.ProductLog;
import com.used.lux.dto.*;
import com.used.lux.dto.admin.AdProductDto;
import com.used.lux.dto.user.auction.AuctionLogDto;
import com.used.lux.dto.user.order.ProductOrderLogDto;
import com.used.lux.dto.user.product.ProductDto;
import com.used.lux.dto.user.product.ProductLogDto;
import com.used.lux.mapper.*;
import com.used.lux.repository.*;
import com.used.lux.repository.appraisal.AppraisalRepository;
import com.used.lux.repository.auction.AuctionLogRepository;
import com.used.lux.repository.auction.AuctionRepository;
import com.used.lux.repository.order.ProductOrderLogRepository;
import com.used.lux.repository.product.ImageRepository;
import com.used.lux.repository.product.ProductLogRepository;
import com.used.lux.repository.product.ProductRepository;
import com.used.lux.request.product.ProductSaveRequest;
import com.used.lux.request.product.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
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
    private final ProductMapper productMapper;

    private final ProductLogRepository productLogRepository;
    private final ProductLogMapper prodLogMapper;

    private final ProductOrderLogRepository productOrderLogRepository;
    private final ProductOrderLogMapper prodOrderLogMapper;

    private final AuctionLogRepository auctionLogRepository;
    private final AuctionLogMapper auctionLogMapper;

    private final AppraisalRepository appraisalRepository;

    private final CategoryBRepository cateBRepo;
    private final CategoryBMapper cateBMapper;


    private final CategoryMRepository categoryMRepository;

    private final BrandRepository brandRepository;

    private final FileHandler fileHandler;

    private final ImageRepository imageRepository;

    private final AuctionRepository auctionRepository;

    private final BrandMapper brandMapper;

    // 상품 리스트 조회
    @Transactional(readOnly = true)
    public Page<ProductDto> getProductList(String productBrand, String productGender,
                                           String productSize, String productGrade, String productState,
                                           String productDate, String query, Pageable pageable) {
        String[] dateResult = productDate.split("-");
        LocalDateTime date = LocalDateTime.of(Integer.parseInt(dateResult[0]),
                Integer.parseInt(dateResult[1]), Integer.parseInt(dateResult[2]), 00, 00);
        return productRepository.findByBackProductList(productBrand, productGender, productSize,
                productGrade, productState, date, query, pageable).map(productMapper::toDtoCustom);
    }

    // 상품 세부사항
    @Transactional(readOnly = true)
    public AdProductDto getProductDetail(Long productId) {
        // 상품 상세
        ProductDto productDto = productMapper.toDtoCustom(productRepository.findById(productId).get());
        // 수정 로그(수정 전)
        List<ProductLogDto> productLogDtos = productLogRepository.findByProdIdOrderByCreatedAtDesc(productId)
                .stream().map(prodLogMapper::toDto).toList();
        // 주문내역
        List<ProductOrderLogDto> productOrderLogDtos = productOrderLogRepository.findByProdId(productId)
                .stream().map(prodOrderLogMapper::toDto).toList();
        Auction auction = auctionRepository.findByProd_Id(productId);
        // 경매내역
        List<AuctionLogDto> auctionLogDtos = null;
        if (auction != null) {
            auctionLogDtos = auctionLogMapper.toDtoList(auctionLogRepository.findByAucId(auction.getId()));
        }

        return AdProductDto.of(productDto, productLogDtos, productOrderLogDtos, auctionLogDtos);
    }

    // 카테고리 리스트 조회
    @Transactional(readOnly = true)
    public List<CategoryBDto> getCategoryList() {
        return cateBMapper.toDtoList(cateBRepo.findAll());
    }

    // 브랜드 리스트 조회
    @Transactional(readOnly = true)
    public List<BrandDto> getBrandList() {
        return brandRepository.findAll()
                .stream().map(brandMapper::toDto).collect(Collectors.toCollection(ArrayList::new));
    }

    // Admin 상품 등록
    @Transactional
    public void productCreate(ProductSaveRequest request) throws Exception {
        Appraisal appraisal = appraisalRepository.getReferenceById(request.appraisalId());
        appraisal.setAppState(AppraisalState.REGISTER);
        Brand brand = brandRepository.findById(request.prodBrandId()).orElse(null);
        CategoryB categoryB = cateBRepo.findById(request.cateBId()).orElse(null);
        CategoryM categoryM = categoryMRepository.findById(request.cateMId()).orElse(null);

        Product product = Product.builder()
                .prodNm(request.prodNm())
                .cateB(categoryB)
                .cateM(categoryM)
                .prodBrand(brand)
                .prodSize(request.prodSize())
                .prodColor(request.prodColor())
                .prodState(ProductState.WAITING)
                .prodGender(GenterType.valueOf(request.prodGenderId()))
                .prodGrade(AppraisalGrade.valueOf(request.prodGradeId()))
                .prodPrice(request.prodPrice())
                .prodContent(request.prodContent())
                .prodSellType(SellType.valueOf(request.prodSellType()))
                .build();
        productRepository.save(product);

        List<Image> imageList = fileHandler.parseFileInfo(request, product); // 파일 처리
        // 파일이 존재할 때에만 처리
        if(!imageList.isEmpty()) {
            for(Image image : imageList) {
                // 파일을 DB에 저장
                System.out.println(image);
                imageRepository.save(image); // 이미지 이름 및 경로 DB에 저장
            }
        }

        if (request.prodSellType().equals("AUCTION")) {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String[] startResult = request.aucStartDate().split("T");
            String[] endResult = request.aucEndDate().split("T");
            LocalDateTime startDate = LocalDateTime.parse(startResult[0] + " " + startResult[1], format);
            LocalDateTime endDate = LocalDateTime.parse(endResult[0] + " " + endResult[1], format);
            Auction auction = Auction.builder()
                    .id(null)
                    .aucState(AuctionState.WAITING)
                    .startPrice(request.startPrice())
                    .presentPrice(request.startPrice())
                    .endPrice(0)
                    .aucStartDate(startDate)
                    .aucEndDate(endDate)
                    .prod(product)
                    .build();
            auctionRepository.save(auction);
        }
    }

    // Admin 상품 업데이트
    @Transactional
    public void productUpdate(Long productId, ProductUpdateRequest request){
        // 업데이트에 필요한 entity 가져오기
        CategoryB categoryB = cateBRepo.findById(request.cateBId()).orElse(null);
        CategoryM categoryM = categoryMRepository.findById(request.cateMId()).orElse(null);
        Brand brand = brandRepository.findById(request.brandId()).orElse(null);
        Product product= productRepository.getReferenceById(productId);

        productLogRepository.save(ProductLog.builder()
                        .id(null)
                        .prodId(productId)
                        .prodNm(request.prodNm())
                        .prodState(ProductState.valueOf(request.prodState()))
                        .prodBrand(brand.getBrandName())
                        .cateBNm(categoryB.getCateBNm()).cateMNm(categoryM.getCateMNm())
                        .prodPrice(request.prodPrice())
                        .prodSellType(SellType.valueOf(request.prodSellType()))
                        .build());

        // 내용 업데이트
        product.setProdNm(request.prodNm());
        product.setProdContent(request.prodContent());
        product.setProdBrand(brand);
        product.setCateB(categoryB);
        product.setCateM(categoryM);
        product.setProdState(ProductState.valueOf(request.prodState()));
        product.setProdPrice(request.prodPrice());
        product.setProdSellType(SellType.valueOf(request.prodSellType()));

        // 레포지토리 저장
        Product result = productRepository.save(product);

//        if (result.getProdSellType().getName().equals("경매")) {
//            Auction auction = auctionRepository.findByProductId(result.getId());
//            if (auction == null) {
//                State auctionState = stateRepository.findByStateStep("경매전");
//                auctionRepository.save(Auction.of(result, auctionState, 0, result.getProductPrice(), 0,
//                        null, null, 0, null));
//            }
//        } else {
//            auctionRepository.deleteByProductId(result.getId());
//        }
        System.out.println("모든 로직 정상 수행");
    }

    public void appraiseProductAddPage() {
    }
}
