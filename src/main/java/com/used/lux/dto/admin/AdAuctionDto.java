package com.used.lux.dto.admin;

import com.used.lux.dto.user.auction.AuctionDto;
import com.used.lux.dto.user.auction.AuctionLogDto;

import java.util.List;

public record AdAuctionDto(
        AuctionDto auc,
        List<AuctionLogDto> aucLogList
) {
    public static AdAuctionDto of(AuctionDto auc, List<AuctionLogDto> aucLogList) {
        return new AdAuctionDto(auc, aucLogList);
    }
}
