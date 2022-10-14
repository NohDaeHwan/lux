package com.used.lux.dto;

import com.used.lux.domain.ProductOrderLog;
import com.used.lux.domain.State;

import java.time.LocalDateTime;

public record ProductOrderLogDto(
        Long id,
        String userEmail,
        Long productId,
        String productName,
        int productPrice,
        String productSellType,
        State productState,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static ProductOrderLogDto of(Long id, String userEmail, Long productId, String productName, int productPrice,
                                        String productSellType, State productState, LocalDateTime createdAt,
                                        String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new ProductOrderLogDto(id, userEmail, productId, productName, productPrice, productSellType,
                productState, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static ProductOrderLogDto from(ProductOrderLog entity) {
        return new ProductOrderLogDto(
                entity.getId(),
                entity.getUserEmail(),
                entity.getProductId(),
                entity.getProductName(),
                entity.getProductPrice(),
                entity.getProductSellType(),
                entity.getProductState(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

}
