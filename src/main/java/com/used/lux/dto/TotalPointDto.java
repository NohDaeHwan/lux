package com.used.lux.dto;

import com.used.lux.domain.UserAccountLog;

import java.time.LocalDateTime;

public record TotalPointDto(Long point) {
    public static TotalPointDto of(Long point) {
        return new TotalPointDto(point);
    }
}
