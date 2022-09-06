package com.used.lux.request;

import com.used.lux.dto.AuctionDto;

public record AuctionBidRequest(
        int price
) {

    public static AuctionBidRequest of(int price) {
        return new AuctionBidRequest(price);
    }

    public AuctionDto toDto() {
        return AuctionDto.of(
                price
        );
    }

}
