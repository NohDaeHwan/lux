package com.used.lux.dto;

import com.used.lux.domain.UserGrade;

public record UserGradeDto(
        Long id,
        int gradeStep,
        String gradeName,
        int discount,
        int rankUp
) {
    public static UserGradeDto of(Long id, int gradeStep, String gradeName, int discount, int rankUp) {
        return new UserGradeDto(id, gradeStep, gradeName, discount, rankUp);
    }

    public static UserGradeDto from(UserGrade entity) {
        return new UserGradeDto(
                entity.getId(),
                entity.getGradeStep(),
                entity.getGradeName(),
                entity.getDiscount(),
                entity.getRankUp()
        );
    }
}
