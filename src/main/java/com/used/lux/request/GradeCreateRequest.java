package com.used.lux.request;

public record GradeCreateRequest(
        int gradeStep,
        String gradeName,
        int discount,
        int rankUp
) {
    public static GradeCreateRequest of(int gradeStep, String gradeName, int discount, int rankUp) {
        return new GradeCreateRequest(gradeStep, gradeName, discount, rankUp);
    }
}