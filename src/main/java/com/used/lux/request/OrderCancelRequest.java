package com.used.lux.request;

public record OrderCancelRequest(
        String stateName
) {
    public static OrderCancelRequest of(String stateName) {
        return new OrderCancelRequest(stateName);
    }
}
