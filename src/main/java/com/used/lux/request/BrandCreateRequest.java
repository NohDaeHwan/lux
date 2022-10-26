package com.used.lux.request;

public record BrandCreateRequest(
        String brandName
) {
    public static BrandCreateRequest of(String brandName) {
        return new BrandCreateRequest(brandName);
    }
}
