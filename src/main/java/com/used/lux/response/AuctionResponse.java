package com.used.lux.response;

import com.used.lux.dto.AuctionDto;

import java.time.LocalDateTime;

public record AuctionResponse(
        Long id,
        String productName,
        String brandName,
        String bigCategory,
        String smallCategory,
        int size,
        String gender,
        String state,
        int price,
        int startPrice,
        int presentPrice,
        int closingPrice,
        LocalDateTime auctionStartDate,
        LocalDateTime auctionClosingDate,
        int viewCount,
        int biddingCount,
        String bidder
) {

    public static AuctionResponse of(Long id, String productName, String brandName, String bigCategory, String smallCategory,
                           int size, String gender, String state, int price, int startPrice, int presentPrice,
                           int closingPrice, LocalDateTime auctionStartDate, LocalDateTime auctionClosingDate,
                           int viewCount, int biddingCount, String bidder) {
        return new AuctionResponse(id, productName, brandName, bigCategory, smallCategory, size, gender, state, price,
                startPrice, presentPrice, closingPrice, auctionStartDate, auctionClosingDate, viewCount, biddingCount,
                bidder);
    }

    public static AuctionResponse from(AuctionDto auctionDto) {
        return new AuctionResponse(
                auctionDto.id(),
                auctionDto.productName(),
                auctionDto.brandName(),
                auctionDto.bigCategory(),
                auctionDto.smallCategory(),
                auctionDto.size(),
                auctionDto.gender(),
                auctionDto.state(),
                auctionDto.price(),
                auctionDto.startPrice(),
                auctionDto.presentPrice(),
                auctionDto.closingPrice(),
                auctionDto.auctionStartDate(),
                auctionDto.auctionClosingDate(),
                auctionDto.viewCount(),
                auctionDto.biddingCount(),
                auctionDto.bidder()
        );
    }

}
