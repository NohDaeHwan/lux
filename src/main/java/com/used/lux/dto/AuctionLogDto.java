package com.used.lux.dto;

import com.used.lux.domain.AuctionLog;

import java.time.LocalDateTime;

public record AuctionLogDto(
        Long id,
        String bidder,
        String productName,
        int presentPrice,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static AuctionLogDto of(Long id, String bidder, String productName, int presentPrice,
                         LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new AuctionLogDto(id, bidder, productName, presentPrice, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static AuctionLogDto from(AuctionLog entity) {
        return new AuctionLogDto(
                entity.getId(),
                entity.getBidder(),
                entity.getProductName(),
                entity.getPresentPrice(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

}
