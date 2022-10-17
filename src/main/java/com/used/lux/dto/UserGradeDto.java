package com.used.lux.dto;

import com.used.lux.domain.UserGrade;

public record UserGradeDto(
        Long id,
        String gradeName,
        int discount
) {
    public static UserGradeDto of(Long id, String gradeName, int discount) {
        return new UserGradeDto(id, gradeName, discount);
    }

    public static UserGradeDto from(UserGrade entity) {
        return new UserGradeDto(
                entity.getId(),
                entity.getGradeName(),
                entity.getDiscount()
        );
    }
}
