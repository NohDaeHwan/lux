package com.used.lux.request;

public record OrderUpdateRequest(
        Long productId,
        Long userId
) {
    public static OrderUpdateRequest of(Long productId, Long userId) {
        return new OrderUpdateRequest(productId, userId);
    }
}
