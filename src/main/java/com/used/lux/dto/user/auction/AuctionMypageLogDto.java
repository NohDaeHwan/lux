package com.used.lux.dto.user.auction;

import java.time.LocalDateTime;

public record AuctionMypageLogDto(
        Long id,
        String bidder,
        String presentBidder,
        Long auctionId,
        Long productId,
        String productName,
        int presentPrice,
        int closingPrice,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static AuctionMypageLogDto of(Long id, String bidder, String presentBidder, Long auctionId, Long productId, String productName,
                                         int presentPrice, int closingPrice, LocalDateTime createdAt, String createdBy,
                                         LocalDateTime modifiedAt, String modifiedBy) {
        return new AuctionMypageLogDto(id, bidder, presentBidder, auctionId, productId, productName, presentPrice,
                closingPrice, createdAt, createdBy, modifiedAt, modifiedBy);
    }
}
