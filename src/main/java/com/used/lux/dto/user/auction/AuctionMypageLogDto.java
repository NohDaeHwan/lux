package com.used.lux.dto.user.auction;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AuctionMypageLogDto(
        Long id,
        Long userId,
        Long presentUserId,
        Long aucId,
        String prodNm,
        long presentPrice,
        long endPrice,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static AuctionMypageLogDto of(Long id, Long userId, Long presentUserId, Long aucId, String prodNm,
                                         long presentPrice, long endPrice, LocalDateTime createdAt, String createdBy,
                                         LocalDateTime modifiedAt, String modifiedBy) {
        return new AuctionMypageLogDto(id, userId, presentUserId, aucId, prodNm, presentPrice,
                endPrice, createdAt, createdBy, modifiedAt, modifiedBy);
    }
}
