package com.used.lux.response.product;

import com.used.lux.domain.State;
import com.used.lux.dto.ProductDto;

import java.time.LocalDateTime;

public record ProductResponse(
        Long id,
        String productName,
        String productBrandName,
        String productGender,
        String productColor,
        String productSize,
        String productGrade,
        String categoryBName,
        String categoryMName,
        State productState,
        int productPrice,
        String productSellType,
        String productContent,
        int productViewCount,
        LocalDateTime createdAt,
        String createdBy
) {

    public static ProductResponse of(Long id, String productName, String productBrandName, String productGender,
                           String productColor, String productSize, String productGrade, String categoryBName,
                                     String categoryMName, State productState, int productPrice, String productSellType,
                           String productContent, int productViewCount, LocalDateTime createdAt, String createdBy) {
        return new ProductResponse(id, productName, productBrandName, productGender, productColor, productSize,
                productGrade, categoryBName, categoryMName, productState, productPrice, productSellType, productContent,
                productViewCount, createdAt, createdBy);
    }

    public static ProductResponse from(ProductDto productDto) {
        return new ProductResponse(
                productDto.id(),
                productDto.productName(),
                productDto.productBrandName(),
                productDto.productGender(),
                productDto.productColor(),
                productDto.productSize(),
                productDto.productGrade(),
                productDto.categoryB().getCategoryBName(),
                productDto.categoryM().getCategoryMName(),
                productDto.productState(),
                productDto.productPrice(),
                productDto.productSellType(),
                productDto.productContent(),
                productDto.productViewCount(),
                productDto.createdAt(),
                productDto.createdBy()
        );
    }

}
