package com.used.lux.request;

public record GradeCreateRequest(
        Long id,
        String gradeName,
        int discount
) {
    public static GradeCreateRequest of(Long id, String gradeName, int discount) {
        return new GradeCreateRequest(id, gradeName, discount);
    }
}