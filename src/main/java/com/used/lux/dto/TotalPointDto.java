package com.used.lux.dto;

public record TotalPointDto(Long point) {
    public static TotalPointDto of(Long point) {
        return new TotalPointDto(point);
    }
}
