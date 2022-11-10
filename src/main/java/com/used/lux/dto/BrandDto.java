package com.used.lux.dto;

import com.used.lux.domain.Brand;
import com.used.lux.domain.CategoryB;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;


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

    public Brand toDto() {
        return Brand.of(id, brandName);
    }

}
