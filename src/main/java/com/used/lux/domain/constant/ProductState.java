package com.used.lux.domain.constant;

import lombok.Getter;

public enum ProductState {
    WAITING("판매대기"),
    SELL("판매중"),
    COMPLETE("판매완료"),
    CANCEL("판매취소");

    @Getter
    private final String name;

    ProductState(String name) {this.name = name;}
}
