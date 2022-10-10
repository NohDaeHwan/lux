package com.used.lux.dto;

import com.used.lux.domain.Auction;
import com.used.lux.domain.Product;

import java.time.LocalDateTime;

public record AuctionDto(
        Long id,
        ProductDto productDto,
        int startPrice,
        int presentPrice,
        int closingPrice,
        LocalDateTime auctionStartDate,
        LocalDateTime auctionClosingDate,
        int biddingCount,
        String bidder,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static AuctionDto of(Long id, ProductDto productDto, int startPrice, int presentPrice, int closingPrice,
                      LocalDateTime auctionStartDate, LocalDateTime auctionClosingDate, int biddingCount,
                      String bidder, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt,
                      String modifiedBy) {
        return new AuctionDto(id, productDto, startPrice, presentPrice, closingPrice, auctionStartDate,
                auctionClosingDate, biddingCount, bidder, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static AuctionDto of(int price) {
        return new AuctionDto(null, null, 0, price, 0,
                null, null, 0, null, null,
                null, null, null);
    }

    public static AuctionDto from(Auction entity) {
        return new AuctionDto(
                entity.getId(),
                ProductDto.from(entity.getProduct()),
                entity.getStartPrice(),
                entity.getPresentPrice(),
                entity.getClosingPrice(),
                entity.getAuctionStartDate(),
                entity.getAuctionClosingDate(),
                entity.getBiddingCount(),
                entity.getBidder(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public Auction toEntity(Product product) {
        return Auction.of(product, startPrice, presentPrice, closingPrice, auctionStartDate, auctionClosingDate,
                biddingCount, bidder);
    }

}
