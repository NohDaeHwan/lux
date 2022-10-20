package com.used.lux.service.admin;


import com.used.lux.domain.CategoryB;
import com.used.lux.domain.CategoryM;
import com.used.lux.domain.Product;
import com.used.lux.domain.QCategoryM;
import com.used.lux.domain.*;
import com.used.lux.dto.*;
import com.used.lux.dto.admin.AdProductDto;
import com.used.lux.repository.*;
import com.used.lux.request.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.used.lux.request.productUpdateRequest;
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

    private final AppraisalRepository appraisalRepository;

    private final CategoryBRepository categoryBRepository;

    private final CategoryMRepository categoryMRepository;

    private final BrandRepository brandRepository;

    public Page<ProductDto> getProductList(Pageable pageable) {
        return productRepository.findAll(pageable).map(ProductDto::from);
    }

    public AdProductDto getProductDetail(Long productId) {
        // 상품 상세
        ProductDto productDto = ProductDto.from(productRepository.findById(productId).get());
        // 수정 로그(수정 전)
        List<ProductLogDto> productLogDtos = productLogRepository.findByProductId(productId)
                .stream().map(ProductLogDto::from).collect(Collectors.toCollection(ArrayList::new));
        // 주문내역
        List<ProductOrderLogDto> productOrderLogDtos = productOrderLogRepository.findByProductId(productId)
                .stream().map(ProductOrderLogDto::from).collect(Collectors.toCollection(ArrayList::new));
        // 경매내역
        List<AuctionLogDto> auctionLogDtos = auctionLogRepository.findByProductId(productId)
                .stream().map(AuctionLogDto::from).collect(Collectors.toCollection(ArrayList::new));
        return AdProductDto.of(productDto, productLogDtos, productOrderLogDtos, auctionLogDtos);

    }

    public List<CategoryBDto> getCategoryList() {
        return categoryBRepository.findAll()
                .stream().map(CategoryBDto::from).collect(Collectors.toCollection(ArrayList::new));
    }



    

    public List<BrandDto> getBrandList() {
        return brandRepository.findAll()
                .stream().map(BrandDto::from).collect(Collectors.toCollection(ArrayList::new));
    }

    public void  productUpdate(Long productId, ProductUpdateRequest productUpdateRequest){
        // 업데이트에 필요한 entity 가져오기
        CategoryB categoryB = categoryBRepository.findByCategoryBName(productUpdateRequest.categoryBName());
        CategoryM categoryM = categoryMRepository.findByCategoryMName(productUpdateRequest.categoryMName());
        Brand brand = brandRepository.findByBrandName(productUpdateRequest.brandName());
        Product product= productRepository.getReferenceById(productId);

        // 내용 업데이트
        product.getAppraisal().setAppraisalProductName(productUpdateRequest.productName());
        product.setProductContent(productUpdateRequest.content());
        product.getAppraisal().setAppraisalBrand(brand);
        product.setCategoryB(categoryB);
        product.setCategoryM(categoryM);
        product.setProductSellType(productUpdateRequest.productSellType());

        // 레포지토리 저장
        appraisalRepository.save(product.getAppraisal());
        productRepository.save(product);
    }


}
