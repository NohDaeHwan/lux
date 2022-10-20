package com.used.lux.request;

public record CategoryCreateRequest(
        String categoryName
) {
    public static CategoryCreateRequest of(String categoryName) {
        return new CategoryCreateRequest(categoryName);
    }
}
