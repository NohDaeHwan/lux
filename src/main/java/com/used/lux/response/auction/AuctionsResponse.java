package com.used.lux.response.auction;

import com.used.lux.dto.user.auction.AuctionDto;

import java.time.LocalDateTime;

public record AuctionsResponse(
        Long id,
        String productName,
        String brandName,
        String productColor,
        Long startPrice,
        Long presentPrice,
        Long closingPrice,
        LocalDateTime auctionStartDate,
        LocalDateTime auctionClosingDate,
        int biddingCount,
        String bidder
) {

    public static AuctionsResponse of(Long id, String productName, String brandName, String productColor,
                                      Long startPrice, Long presentPrice, Long closingPrice,
                                      LocalDateTime auctionStartDate, LocalDateTime auctionClosingDate,
                                     int biddingCount, String bidder) {
        return new AuctionsResponse(id, productName, brandName, productColor, startPrice, presentPrice, closingPrice, auctionStartDate,
                auctionClosingDate, biddingCount, bidder);
    }

    public static AuctionsResponse from(AuctionDto auctionDto) {
        return new AuctionsResponse(
                auctionDto.id(),
                auctionDto.aucNm(),
                auctionDto.aucBrand(),
                auctionDto.aucColor(),
                auctionDto.startPrice(),
                auctionDto.presentPrice(),
                auctionDto.endPrice(),
                auctionDto.aucStartDate(),
                auctionDto.aucEndDate(),
                auctionDto.biddingCount(),
                auctionDto.bidder()
        );
    }

}
