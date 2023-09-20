package com.used.lux.request.order;

public record OrderCreateRequest(
        String name,
        String phoneNumber,
        String address,
        String email,
        Long payment,
        String requestTerm) {
    public static OrderCreateRequest of(String name, String phoneNumber, String address, String email, Long payment, String requestTerm) {
        return new OrderCreateRequest(name, phoneNumber, address, email, payment, requestTerm);
    }
}
