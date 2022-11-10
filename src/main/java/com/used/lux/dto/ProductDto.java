package com.used.lux.dto;

import com.used.lux.domain.*;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record ProductDto(
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
        Set<ImageDto> imageDtos,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static ProductDto of(Long id, String productName, String brandName, String gender, String color, String size, String grade,
                                String categoryBName, String categoryMName, State productState, int productPrice,
                                String productSellType, String productContent, int productViewCount, Set<ImageDto> imageDtos,
                                LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new ProductDto(id, productName, brandName, gender, color, size, grade, categoryBName, categoryMName, productState,
                productPrice, productSellType, productContent, productViewCount, imageDtos, createdAt, createdBy,
                modifiedAt, modifiedBy);
    }

    public static ProductDto from(Product entity) {
        return new ProductDto(
                entity.getId(),
                entity.getAppraisalRequest().getAppraisalProductName(),
                entity.getAppraisalRequest().getAppraisalBrand().getBrandName(),
                entity.getAppraisalRequest().getAppraisalGender(),
                entity.getAppraisalRequest().getAppraisalColor(),
                entity.getAppraisalRequest().getAppraisalSize(),
                "S", // 수정필요
                entity.getCategoryB().getCategoryBName(),
                entity.getCategoryM().getCategoryMName(),
                entity.getState(),
                entity.getProductPrice(),
                entity.getProductSellType(),
                entity.getProductContent(),
                entity.getProductViewCount(),
                entity.getImages().stream()
                        .map(ImageDto::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new)),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public Product toEntity(AppraisalRequest appraisalRequest, CategoryB categoryB, CategoryM categoryM) {
        return Product.of(appraisalRequest, categoryB, categoryM, productState, productPrice, productSellType, productContent,
                productViewCount);
    }

}
