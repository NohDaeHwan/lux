package com.used.lux.dto.user.useraccount;

import java.time.LocalDateTime;

public record UserAccountLogDto(
        Long id,
        String userEmail,
        String gradeName,
        int discount,
        long point,
        String usageDetail,
        String saleNumber,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
}
