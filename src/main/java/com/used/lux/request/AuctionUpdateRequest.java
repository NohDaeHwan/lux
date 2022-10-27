package com.used.lux.request;

public record AuctionUpdateRequest(

        int startPrice,

        String auctionStartDate,

        String auctionClosingDate

) {

    public  static AuctionUpdateRequest of(int startPrice, String auctionStartDate, String auctionClosingDate){
        return  new AuctionUpdateRequest(startPrice,auctionStartDate,auctionClosingDate);
    }



}
