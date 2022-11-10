package com.used.lux.request;

public record AuctionBidRequest(
        int price
) {
    public static AuctionBidRequest of(int price) {
        return new AuctionBidRequest(price);
    }

}
