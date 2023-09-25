package com.used.lux.dto.user.auction;

import java.time.LocalDateTime;

/**
 * DTO for {@link com.used.lux.domain.auction.Auction}
 */
public record AuctionDto(
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy,
        Long id,
        String aucState,
        Long startPrice,
        Long presentPrice,
        Long endPrice,
        LocalDateTime aucStartDate,
        LocalDateTime aucEndDate,
        int biddingCnt,
        String bidder
) {
}
