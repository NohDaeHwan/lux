package com.used.lux.dto;

import com.used.lux.domain.*;

import java.time.LocalDateTime;

public record ProductDto(
        Long id,
        String productName,
        String productBrandName,
        String productGender,
        String productColor,
        String productSize,
        String productGrade,
        CategoryB categoryB,
        CategoryM categoryM,
        State productState,
        int productPrice,
        String productSellType,
        String productContent,
        int productViewCount,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static ProductDto of(Long id, String productName, String brandName, String gender, String color, String size, String grade,
                                CategoryB categoryB, CategoryM categoryM, State productState, int productPrice,
                                String productSellType, String productContent, int productViewCount, LocalDateTime createdAt,
                                String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new ProductDto(id, productName, brandName, gender, color, size, grade, categoryB, categoryM, productState,
                productPrice, productSellType, productContent, productViewCount, createdAt, createdBy,
                modifiedAt, modifiedBy);
    }

    public static ProductDto from(Product entity) {
        return new ProductDto(
                entity.getId(),
                entity.getAppraisal().getAppraisalProductName(),
                entity.getAppraisal().getAppraisalBrand().getBrandName(),
                entity.getAppraisal().getAppraisalGender(),
                entity.getAppraisal().getAppraisalColor(),
                entity.getAppraisal().getAppraisalSize(),
                entity.getAppraisal().getAppraisalGrade(),
                entity.getCategoryB(),
                entity.getCategoryM(),
                entity.getState(),
                entity.getProductPrice(),
                entity.getProductSellType(),
                entity.getProductContent(),
                entity.getProductViewCount(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public Product toEntity(Appraisal appraisal) {
        return Product.of(appraisal, categoryB, categoryM, productState, productPrice, productSellType, productContent,
                productViewCount);
    }

}
