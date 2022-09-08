package com.used.lux.response.product;

import com.used.lux.dto.ProductDto;

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
        int paymentComple
) {

    public static ProductResponse of(Long id, String productName, String brandName, String bigCategory, String smallCategory, int size,
                           String gender, String state, int price, int paymentComple) {
        return new ProductResponse(id, productName, brandName, bigCategory, smallCategory, size, gender, state, price, paymentComple);
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
                productDto.paymentComple()
        );
    }

}
