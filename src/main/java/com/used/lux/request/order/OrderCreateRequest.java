package com.used.lux.request.order;

public record OrderCreateRequest(
        String name,
        String phoneNumber,
        String address,
        String email,
        Long payment,
        String requestTerm) {
}
