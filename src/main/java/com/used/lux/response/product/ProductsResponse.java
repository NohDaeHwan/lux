package com.used.lux.response.product;

import com.used.lux.domain.State;
import com.used.lux.dto.ProductDto;

import java.time.LocalDateTime;

public record ProductsResponse(
        Long id,
        String productName,
        String productGrade,
        State productState,
        int productPrice,
        String productSellType,
        int productViewCount,
        LocalDateTime createdAt,
        String createdBy
)  {

    public static ProductsResponse of(Long id, String productName, String productGrade, State productState, int productPrice,
                            String productSellType, int productViewCount, LocalDateTime createdAt, String createdBy) {
        return new ProductsResponse(id, productName, productGrade, productState, productPrice, productSellType,
                productViewCount, createdAt, createdBy);
    }

    public static ProductsResponse from(ProductDto productDto) {
        return new ProductsResponse(
                productDto.id(),
                productDto.productName(),
                productDto.productGrade(),
                productDto.productState(),
                productDto.productPrice(),
                productDto.productSellType(),
                productDto.productViewCount(),
                productDto.createdAt(),
                productDto.createdBy()
        );
    }

}
