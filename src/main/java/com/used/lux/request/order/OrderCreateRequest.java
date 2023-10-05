package com.used.lux.request.order;

public record OrderCreateRequest(
        Long prodId,
        String name,
        String phoneNumber,
        String zoneCode,
        String addr,
        Long payment,
        String requestTerm
) {
}
