package com.used.lux.request;

public record AuctionUpdateRequest(

        int startPrice,
        int presentPrice,
        String auctionStartDate,
        String auctionClosingDate

) {

    public  static AuctionUpdateRequest of(int startPrice, int presentPrice, String auctionStartDate, String auctionClosingDate){
        return  new AuctionUpdateRequest(startPrice, presentPrice, auctionStartDate, auctionClosingDate);
    }



}
