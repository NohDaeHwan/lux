package com.used.lux.response;

import com.used.lux.dto.ProductDto;

public record ProductsResponse(
        Long id,
        ImageResponse imageResponse,
        String productName,
        String state,
        int price
)  {

    public static ProductsResponse of(Long id, ImageResponse imageResponse, String productName, String state, int price) {
        return new ProductsResponse(id, imageResponse, productName, state, price);
    }

    public static ProductsResponse from(ProductDto productDto) {
        return new ProductsResponse(
                productDto.id(),
                ImageResponse.from(productDto.imageDto()),
                productDto.productName(),
                productDto.state(),
                productDto.price()
        );
    }

}
