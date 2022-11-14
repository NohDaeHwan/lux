package com.used.lux.request;

public record CategoryCreateRequest(
        String categoryName,
        String categoryType,
        Long Bid
) {


    public static CategoryCreateRequest of(String categoryName , String categoryType ,Long Bid) {
        return new CategoryCreateRequest(categoryName,categoryType,Bid);
    }


}
