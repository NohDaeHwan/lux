package com.used.lux.dto.user.product;

import com.used.lux.domain.State;
import com.used.lux.domain.product.ProductLog;

import java.time.LocalDateTime;

public record ProductLogDto(
        Long id,
        Long productId,
        String productName,
        State productState,
        String categoryBName,
        String categoryMName,
        int productPrice,
        String productSellType,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static ProductLogDto of(Long id, Long productId, String productName, State productState, String categoryBName,
                         String categoryMName, int productPrice, String productSellType, LocalDateTime createdAt,
                         String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new ProductLogDto(id, productId, productName, productState, categoryBName, categoryMName,
                productPrice, productSellType, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static ProductLogDto from(ProductLog entity) {
        return new ProductLogDto(
                entity.getId(),
                entity.getProductId(),
                entity.getProductName(),
                entity.getProductState(),
                entity.getCategoryB().getCateBNm(),
                entity.getCategoryM().getCateMNm(),
                entity.getProductPrice(),
                entity.getProductSellType(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }
}
