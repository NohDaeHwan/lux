package com.used.lux.dto.user.product;

import java.time.LocalDateTime;

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
