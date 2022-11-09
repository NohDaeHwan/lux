package com.used.lux.request;

public record OrderCreateRequest(
        String name,
        String phoneNumber,
        String address,
        String email,
        String requestTerm) {
    public static OrderCreateRequest of(String name, String phoneNumber, String address, String email, String requestTerm) {
        return new OrderCreateRequest(name, phoneNumber, address, email, requestTerm);
    }
}
