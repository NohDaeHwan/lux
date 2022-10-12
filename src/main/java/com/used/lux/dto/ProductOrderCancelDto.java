package com.used.lux.dto;

import com.used.lux.domain.ProductOrderCancel;

import java.time.LocalDateTime;

public record ProductOrderCancelDto(
        Long id,
        String userName,
        String productName,
        int productPrice,
        String cancelTerm,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static ProductOrderCancelDto of(Long id, String userName, String productName, int productPrice, String cancelTerm,
                                 LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new ProductOrderCancelDto(id, userName, productName, productPrice, cancelTerm, createdAt,
                createdBy, modifiedAt, modifiedBy);
    }

    public static ProductOrderCancelDto from(ProductOrderCancel entity) {
        return new ProductOrderCancelDto(
                entity.getId(),
                entity.getUserName(),
                entity.getProductName(),
                entity.getProductPrice(),
                entity.getCancelTerm(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

}
