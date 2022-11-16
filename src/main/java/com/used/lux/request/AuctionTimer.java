package com.used.lux.request;

public record AuctionTimer(
        int stateId
) {

    public  static AuctionTimer of(int stateId){
        return  new AuctionTimer(stateId);
    }


}
