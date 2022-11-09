package com.used.lux.response.auction;

import com.used.lux.dto.AuctionDto;
import com.used.lux.response.StateResponse;
import com.used.lux.response.product.ProductResponse;

import java.time.LocalDateTime;

public record AuctionResponse(
        Long id,
        ProductResponse productResponse,
        String stateName,
        String stateStep,
        int startPrice,
        int presentPrice,
        int closingPrice,
        LocalDateTime auctionStartDate,
        LocalDateTime auctionClosingDate,
        int biddingCount,
        String bidder
) {

    public static AuctionResponse of(Long id, ProductResponse productResponse, String stateName, String stateStep,
                                     int startPrice, int presentPrice, int closingPrice, LocalDateTime auctionStartDate,
                                     LocalDateTime auctionClosingDate, int biddingCount, String bidder) {
        return new AuctionResponse(id, productResponse, stateName, stateStep, startPrice, presentPrice, closingPrice, auctionStartDate,
                auctionClosingDate, biddingCount, bidder);
    }

    public static AuctionResponse from(AuctionDto auctionDto) {
        return new AuctionResponse(
                auctionDto.id(),
                ProductResponse.from(auctionDto.productDto()),
                auctionDto.stateDto().stateName(),
                auctionDto.stateDto().stateStep(),
                auctionDto.startPrice(),
                auctionDto.presentPrice(),
                auctionDto.closingPrice(),
                auctionDto.auctionStartDate(),
                auctionDto.auctionClosingDate(),
                auctionDto.biddingCount(),
                auctionDto.bidder()
        );
    }

}
