package com.used.lux.response.auction;

import com.used.lux.dto.AuctionDto;
import com.used.lux.response.StateResponse;
import com.used.lux.response.product.ProductResponse;

import java.time.LocalDateTime;

public record AuctionResponse(
        Long id,
        ProductResponse productResponse,
        StateResponse stateResponse,
        int startPrice,
        int presentPrice,
        int closingPrice,
        LocalDateTime auctionStartDate,
        LocalDateTime auctionClosingDate,
        int biddingCount,
        String bidder
) {

    public static AuctionResponse of(Long id, ProductResponse productResponse, StateResponse stateResponse, int startPrice, int presentPrice,
                           int closingPrice, LocalDateTime auctionStartDate, LocalDateTime auctionClosingDate,
                           int biddingCount, String bidder) {
        return new AuctionResponse(id, productResponse, stateResponse, startPrice, presentPrice, closingPrice, auctionStartDate,
                auctionClosingDate, biddingCount, bidder);
    }

    public static AuctionResponse from(AuctionDto auctionDto) {
        return new AuctionResponse(
                auctionDto.id(),
                ProductResponse.from(auctionDto.productDto()),
                StateResponse.from(auctionDto.stateDto()),
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
