package com.used.lux.service;

import com.used.lux.dto.*;
import com.used.lux.dto.admin.AdCategoryDto;
import com.used.lux.dto.admin.AdProductDto;
import com.used.lux.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    private final CategoryBRepository categoryBRepository;

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

    public AdCategoryDto getCategoryList() {
        List<CategoryBDto> categoryBDtos = categoryBRepository.findAll()
                .stream().map(CategoryBDto::from).collect(Collectors.toCollection(ArrayList::new));
        List<BrandDto> brandDtos = brandRepository.findAll()
                .stream().map(BrandDto::from).collect(Collectors.toCollection(ArrayList::new));
        return AdCategoryDto.of(categoryBDtos, brandDtos);
    }
}
