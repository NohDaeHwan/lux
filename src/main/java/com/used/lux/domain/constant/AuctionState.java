package com.used.lux.domain.constant;

import lombok.Getter;

public enum AuctionState {
    WAITING("경매대기중"),
    SELL("경매중"),
    PAY_WAITING("결제대기중"),
    END("판매완료");

    @Getter
    private final String name;

    AuctionState(String name) {this.name = name;}
}
