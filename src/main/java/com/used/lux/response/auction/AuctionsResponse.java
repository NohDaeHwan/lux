package com.used.lux.response.auction;

import com.used.lux.dto.AuctionDto;
import com.used.lux.dto.ImageDto;
import com.used.lux.response.product.ProductsResponse;

import java.time.LocalDateTime;
import java.util.Set;

public record AuctionsResponse(
        Long id,
        String productName,
        String brandName,
        String productColor,
        Set<ImageDto> imageDtos,
        int startPrice,
        int presentPrice,
        int closingPrice,
        LocalDateTime auctionStartDate,
        LocalDateTime auctionClosingDate,
        int biddingCount,
        String bidder
) {

    public static AuctionsResponse of(Long id, String productName, String brandName, String productColor, Set<ImageDto> imageDtos,
                                      int startPrice, int presentPrice, int closingPrice,
                                      LocalDateTime auctionStartDate, LocalDateTime auctionClosingDate,
                                     int biddingCount, String bidder) {
        return new AuctionsResponse(id, productName, brandName, productColor, imageDtos, startPrice, presentPrice, closingPrice, auctionStartDate,
                auctionClosingDate, biddingCount, bidder);
    }

    public static AuctionsResponse from(AuctionDto auctionDto) {
        return new AuctionsResponse(
                auctionDto.id(),
                auctionDto.productDto().productName(),
                auctionDto.productDto().productBrandName(),
                auctionDto.productDto().productColor(),
                auctionDto.productDto().imageDtos(),
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
