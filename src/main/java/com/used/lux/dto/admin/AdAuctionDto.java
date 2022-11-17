package com.used.lux.dto.admin;

import com.used.lux.dto.user.auction.AuctionDto;
import com.used.lux.dto.user.auction.AuctionLogDto;

import java.util.List;

public record AdAuctionDto(
        AuctionDto auctionDto,
        List<AuctionLogDto> auctionLogDtos
) {
    public static AdAuctionDto of(AuctionDto auctionDto, List<AuctionLogDto> auctionLogDtos) {
        return new AdAuctionDto(auctionDto, auctionLogDtos);
    }
}
