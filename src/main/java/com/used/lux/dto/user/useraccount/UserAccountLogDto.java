package com.used.lux.dto.user.useraccount;

import com.used.lux.domain.useraccount.UserAccountLog;

import java.time.LocalDateTime;

public record UserAccountLogDto(
        Long id,
        String userEmail,
        String gradeName,
        int discount,
        int point,
        String usageDetail,
        String saleNumber,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static UserAccountLogDto of(Long id, String userEmail, String gradeName, int discount, int point,
                                       String usageDetail, String saleNumber, LocalDateTime createdAt,
                                       String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new UserAccountLogDto(id, userEmail, gradeName, discount, point, usageDetail, saleNumber, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static UserAccountLogDto from(UserAccountLog entity) {
        return new UserAccountLogDto(
                entity.getId(),
                entity.getUserEmail(),
                entity.getUserGrade().getGradeName(),
                entity.getUserGrade().getDiscount(),
                entity.getPoint(),
                entity.getUsageDetail(),
                entity.getSaleNumber(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

}
