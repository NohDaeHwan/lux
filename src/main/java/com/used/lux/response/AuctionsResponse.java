package com.used.lux.response;

import com.used.lux.dto.AuctionDto;

import java.time.LocalDateTime;

public record AuctionsResponse(
        Long id,
        String productName,
        int presentPrice,
        int closingPrice,
        LocalDateTime auctionClosingDate,
        int viewCount,
        int biddingCount
) {

    public static AuctionsResponse of(Long id, String productName, int presentPrice, int closingPrice,
                                      LocalDateTime auctionClosingDate, int viewCount, int biddingCount) {
        return new AuctionsResponse(id, productName, presentPrice, closingPrice, auctionClosingDate, viewCount, biddingCount);
    }

    public static AuctionsResponse from(AuctionDto auctionDto) {
        return new AuctionsResponse(
                auctionDto.id(),
                auctionDto.productName(),
                auctionDto.presentPrice(),
                auctionDto.closingPrice(),
                auctionDto.auctionClosingDate(),
                auctionDto.viewCount(),
                auctionDto.biddingCount()
        );
    }

}
