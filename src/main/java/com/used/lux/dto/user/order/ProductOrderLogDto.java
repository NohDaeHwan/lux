package com.used.lux.dto.user.order;

import java.time.LocalDateTime;

public record ProductOrderLogDto(
        Long id,
        Long userId,
        Long prodId,
        String prodNm,
        String prodState,
        long orderPrice,
        String prodSellType,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
}
