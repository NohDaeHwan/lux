package com.used.lux.response.auction;

import com.used.lux.response.product.ProductResponse;

import java.time.LocalDateTime;

public record AuctionResponse(
        Long id,
        ProductResponse productResponse,
        String stateName,
        int startPrice,
        int presentPrice,
        int closingPrice,
        LocalDateTime auctionStartDate,
        LocalDateTime auctionClosingDate,
        int biddingCount,
        String bidder
) {
}
