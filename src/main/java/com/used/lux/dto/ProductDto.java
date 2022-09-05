package com.used.lux.dto;

import com.used.lux.domain.Product;

import java.time.LocalDateTime;

public record ProductDto(
        Long id,
        ImageDto imageDto,
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
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static ProductDto of(Long id, ImageDto imageDto, String productName, String brandName, String bigCategory, String smallCategory,
                                int size, String gender, String state, int price, int paymentComple, LocalDateTime createdAt,
                                String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new ProductDto(id, imageDto, productName, brandName, bigCategory, smallCategory, size, gender, state,
                price, paymentComple, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static ProductDto from(Product entity) {
        return new ProductDto(
                entity.getId(),
                ImageDto.from(entity.getImage()),
                entity.getProductName(),
                entity.getBrandName(),
                entity.getBigCategory(),
                entity.getSmallCategory(),
                entity.getSize(),
                entity.getGender(),
                entity.getState(),
                entity.getPrice(),
                entity.getPaymentComple(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public Product toEntity() {
        return Product.of(productName, brandName, bigCategory, smallCategory, size, gender, state, price, paymentComple, imageDto.toEntity());
    }

}
