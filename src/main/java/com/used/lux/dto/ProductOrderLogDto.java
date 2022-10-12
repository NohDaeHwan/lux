package com.used.lux.dto;

import com.used.lux.domain.ProductOrderLog;

import java.time.LocalDateTime;

public record ProductOrderLogDto(
        Long id,
        String userEmail,
        String productName,
        int productPrice,
        String productSellType,
        String stateName,
        String stateStep,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static ProductOrderLogDto of(Long id, String userEmail, String productName, int productPrice,
                                        String productSellType, String stateName, String stateStep,
                                        LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new ProductOrderLogDto(id, userEmail, productName, productPrice, productSellType,
                stateName, stateStep, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static ProductOrderLogDto from(ProductOrderLog entity) {
        return new ProductOrderLogDto(
                entity.getId(),
                entity.getUserEmail(),
                entity.getProductName(),
                entity.getProductPrice(),
                entity.getProductSellType(),
                entity.getProductState().getStateName(),
                entity.getProductState().getStateStep(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

}
