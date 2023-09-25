package com.used.lux.dto.user.product;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ImageDto(
        Long productId,
        String origFileName,
        String filePath,
        Long fileSize,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
}
