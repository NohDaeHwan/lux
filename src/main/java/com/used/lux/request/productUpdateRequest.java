package com.used.lux.request;

import com.used.lux.dto.AuctionDto;

public record productUpdateRequest(

        String productName,
        String Content,
        String BrandName,
        String categoryBName,
        String categoryMName,
        String productSellType

) {

    public static productUpdateRequest of(  String productSellType,
                                            String categoryMName,
                                            String categoryBName,
                                            String BrandName,
                                            String Content,
                                            String productName) {
        return new productUpdateRequest(productSellType,categoryMName,categoryBName,BrandName,Content,productName);
    }



}
