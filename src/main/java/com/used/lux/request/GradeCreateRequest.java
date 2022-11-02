package com.used.lux.request;

public record GradeCreateRequest(
        Long id,
        int gradeStep,
        String gradeName,
        int discount,
        int rankUp
) {
    public static GradeCreateRequest of(Long id, int gradeStep, String gradeName, int discount, int rankUp) {
        return new GradeCreateRequest(id, gradeStep, gradeName, discount, rankUp);
    }
}