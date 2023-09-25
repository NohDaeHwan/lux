package com.used.lux.response.useraccount;

import com.used.lux.dto.user.useraccount.UserAccountLogDto;

import java.time.LocalDateTime;

public record UserAccountLogResponse(
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
    public static UserAccountLogResponse of(Long id, String userEmail, String gradeName, int discount, int point,
                                            String usageDetail, String saleNumber, LocalDateTime createdAt,
                                            String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new UserAccountLogResponse(id, userEmail, gradeName, discount, point, usageDetail,
                saleNumber, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static UserAccountLogResponse from(UserAccountLogDto dto) {
        return new UserAccountLogResponse(
                dto.id(),
                dto.userEmail(),
                dto.gradeName(),
                dto.discount(),
                dto.point(),
                dto.usageDetail(),
                dto.saleNumber(),
                dto.createdAt(),
                dto.createdBy(),
                dto.modifiedAt(),
                dto.modifiedBy()
        );
    }
}
