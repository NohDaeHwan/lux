package com.used.lux.domain.constant;

import lombok.Getter;

public enum AppraisalState {
    BEFORE("검수전"),
    INSPECTION("검수중"),
    COMPLETE("검수완료"),
    REJECT("매입거부"),
    REFUSE_TO_SELL("판매거절"),
    SELL("매입완료"),
    REGISTER("상품등록");

    @Getter
    private final String name;

    AppraisalState(String name) {this.name = name;}
}
