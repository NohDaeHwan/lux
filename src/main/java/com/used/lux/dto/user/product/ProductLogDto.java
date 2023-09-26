package com.used.lux.dto.user.product;

import java.time.LocalDateTime;

public record ProductLogDto(
        Long id,
        Long prodId,
        String prodNm,
        String prodState,
        String prodBrand,
        String cateBNm,
        String cateMNm,
        long prodPrice,
        String prodSellType,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
}
