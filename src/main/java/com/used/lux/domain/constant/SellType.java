package com.used.lux.domain.constant;

import lombok.Getter;

public enum SellType {
    USED("중고"),
    AUCTION("경매");

    @Getter
    private final String name;

    SellType(String name) {this.name = name;}
}
