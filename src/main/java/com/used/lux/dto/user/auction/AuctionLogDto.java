package com.used.lux.dto.user.auction;

import com.used.lux.domain.auction.AuctionLog;

import java.time.LocalDateTime;

public record AuctionLogDto(
        Long id,
        String bidder,
        Long auctionId,
        Long productId,
        String productName,
        int presentPrice,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static AuctionLogDto of(Long id, String bidder, Long auctionId, Long productId, String productName, int presentPrice,
                         LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new AuctionLogDto(id, bidder, auctionId, productId, productName, presentPrice, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static AuctionLogDto from(AuctionLog entity) {
        return new AuctionLogDto(
                entity.getId(),
                entity.getBidder(),
                entity.getAuctionId(),
                entity.getProductId(),
                entity.getProductName(),
                entity.getPresentPrice(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

}
