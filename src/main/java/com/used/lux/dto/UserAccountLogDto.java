package com.used.lux.dto;

import com.used.lux.domain.UserAccountLog;

import java.time.LocalDateTime;

public record UserAccountLogDto(
        Long id,
        String userEmail,
        int point,
        String gradeName,
        int discount,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static UserAccountLogDto of(Long id, String userEmail, int point, String gradeName, int discount,
                             LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new UserAccountLogDto(id, userEmail, point, gradeName, discount, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static UserAccountLogDto from(UserAccountLog entity) {
        return new UserAccountLogDto(
                entity.getId(),
                entity.getUserEmail(),
                entity.getPoint(),
                entity.getUserGrade().getGradeName(),
                entity.getUserGrade().getDiscount(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

}
