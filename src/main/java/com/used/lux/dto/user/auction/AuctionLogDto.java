package com.used.lux.dto.user.auction;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AuctionLogDto(
        Long id,
        Long userId,
        Long aucId,
        String prodNm,
        long presentPrice,
        String bidder,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
}
