package com.used.lux.request.auction;

public record AuctionTimerRequest(
        int stateId
) {

    public  static AuctionTimerRequest of(int stateId){
        return  new AuctionTimerRequest(stateId);
    }


}
