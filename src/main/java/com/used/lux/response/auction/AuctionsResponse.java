package com.used.lux.response.auction;

import com.used.lux.dto.user.auction.AuctionDto;

import java.time.LocalDateTime;

public record AuctionsResponse(
        Long id,
        Long startPrice,
        Long presentPrice,
        Long closingPrice,
        LocalDateTime auctionStartDate,
        LocalDateTime auctionClosingDate,
        int biddingCount,
        String bidder
) {
}
