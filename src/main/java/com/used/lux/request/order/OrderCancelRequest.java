package com.used.lux.request.order;

public record OrderCancelRequest(
        String stateName
) {
    public static OrderCancelRequest of(String stateName) {
        return new OrderCancelRequest(stateName);
    }
}
