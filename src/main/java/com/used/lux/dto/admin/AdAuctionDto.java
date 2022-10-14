package com.used.lux.dto.admin;

import com.used.lux.dto.AuctionDto;
import com.used.lux.dto.AuctionLogDto;
import com.used.lux.dto.BrandDto;
import com.used.lux.dto.CategoryBDto;

import java.util.List;

public record AdAuctionDto(
        AuctionDto auctionDto,
        List<AuctionLogDto> auctionLogDtos
) {
    public static AdAuctionDto of(AuctionDto auctionDto, List<AuctionLogDto> auctionLogDtos) {
        return new AdAuctionDto(auctionDto, auctionLogDtos);
    }
}
