package com.used.lux.response;

import com.used.lux.dto.ProductDto;

import java.time.LocalDateTime;

public record ProductsResponse(
        Long id,
        ImageResponse imageResponse,
        String productName,
        String state,
        int price,
        LocalDateTime createdAt,
        String createdBy
)  {

    public static ProductsResponse of(Long id, ImageResponse imageResponse, String productName, String state, int price, LocalDateTime createdAt, String createdBy) {
        return new ProductsResponse(id, imageResponse, productName, state, price, createdAt, createdBy);
    }

    public static ProductsResponse from(ProductDto productDto) {
        return new ProductsResponse(
                productDto.id(),
                ImageResponse.from(productDto.imageDto()),
                productDto.productName(),
                productDto.state(),
                productDto.price(),
                productDto.createdAt(),
                productDto.createdBy()
        );
    }

}
