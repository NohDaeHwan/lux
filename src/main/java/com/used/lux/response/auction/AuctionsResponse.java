package com.used.lux.response.auction;

import com.used.lux.dto.AuctionDto;
import com.used.lux.response.product.ProductsResponse;

import java.time.LocalDateTime;

public record AuctionsResponse(
        Long id,
        ProductsResponse productsResponse,
        int startPrice,
        int presentPrice,
        int closingPrice,
        LocalDateTime auctionStartDate,
        LocalDateTime auctionClosingDate,
        int biddingCount,
        String bidder
) {

    public static AuctionsResponse of(Long id, ProductsResponse productsResponse, int startPrice, int presentPrice,
                                     int closingPrice, LocalDateTime auctionStartDate, LocalDateTime auctionClosingDate,
                                     int biddingCount, String bidder) {
        return new AuctionsResponse(id, productsResponse, startPrice, presentPrice, closingPrice, auctionStartDate,
                auctionClosingDate, biddingCount, bidder);
    }

    public static AuctionsResponse from(AuctionDto auctionDto) {
        return new AuctionsResponse(
                auctionDto.id(),
                ProductsResponse.from(auctionDto.productDto()),
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
