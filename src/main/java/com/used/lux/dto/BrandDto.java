package com.used.lux.dto;

import com.used.lux.domain.Brand;

public record BrandDto(
        Long id,
        String brandName
) {
    public static BrandDto of(Long id, String brandName) {
        return new BrandDto(id, brandName);
    }

    public static BrandDto from(Brand entity) {
        return new BrandDto(entity.getId(), entity.getBrandName());
    }
}
