package com.used.lux.response;

import com.used.lux.domain.UserAccountLog;
import com.used.lux.dto.UserAccountLogDto;

import java.time.LocalDateTime;

public record UserAccountLogResponse(
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
    public static UserAccountLogResponse of(Long id, String userEmail, int point, String gradeName, int discount,
                                       LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new UserAccountLogResponse(id, userEmail, point, gradeName, discount, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static UserAccountLogResponse from(UserAccountLogDto dto) {
        return new UserAccountLogResponse(
                dto.id(),
                dto.userEmail(),
                dto.point(),
                dto.gradeName(),
                dto.discount(),
                dto.createdAt(),
                dto.createdBy(),
                dto.modifiedAt(),
                dto.modifiedBy()
        );
    }
}
