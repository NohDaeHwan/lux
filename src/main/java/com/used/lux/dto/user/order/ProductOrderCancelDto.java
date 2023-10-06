package com.used.lux.dto.user.order;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ProductOrderCancelDto(
        Long id,
        String userNm,
        String prodNm,
        Long prodPrice,
        String cancelTerm,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
}
