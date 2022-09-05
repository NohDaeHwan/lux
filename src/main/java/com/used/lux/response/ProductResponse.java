package com.used.lux.response;

import com.used.lux.dto.ProductDto;

import java.time.LocalDateTime;

public record ProductResponse(
        Long id,
        String productName,
        String brandName,
        String bigCategory,
        String smallCategory,
        int size,
        String gender,
        String state,
        int price,
        int paymentComple,
        LocalDateTime createdAt,
        String createdBy
) {

    public static ProductResponse of(Long id, String productName, String brandName, String bigCategory, String smallCategory, int size,
                           String gender, String state, int price, int paymentComple, LocalDateTime createdAt, String createdBy) {
        return new ProductResponse(id, productName, brandName, bigCategory, smallCategory, size, gender, state, price, paymentComple, createdAt, createdBy);
    }

    public static ProductResponse from(ProductDto productDto) {
        return new ProductResponse(
                productDto.id(),
                productDto.productName(),
                productDto.brandName(),
                productDto.bigCategory(),
                productDto.smallCategory(),
                productDto.size(),
                productDto.gender(),
                productDto.state(),
                productDto.price(),
                productDto.paymentComple(),
                productDto.createdAt(),
                productDto.createdBy()
        );
    }

}
