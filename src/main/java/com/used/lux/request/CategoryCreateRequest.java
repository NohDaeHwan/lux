package com.used.lux.request;

public record CategoryCreateRequest(
        String categoryName,
        String categoryType,
        Long Bid
) {
}
