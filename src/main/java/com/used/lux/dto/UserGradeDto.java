package com.used.lux.dto;

public record UserGradeDto(
        Long id,
        int gradeStep,
        String gradeName,
        int discount,
        int rankUp
) {
}
