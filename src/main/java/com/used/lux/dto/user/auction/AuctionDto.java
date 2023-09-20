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
        String aucNm,
        String cateBNm,
        String cateMNm,
        String aucState,
        String aucGrade,
        String aucBrand,
        String aucGender,
        String aucSize,
        String aucColor,
        String aucContent,
        int aucViewCnt,
        Long startPrice,
        Long presentPrice,
        Long endPrice,
        LocalDateTime aucStartDate,
        LocalDateTime aucEndDate,
        int biddingCount,
        String bidder
) {
}
