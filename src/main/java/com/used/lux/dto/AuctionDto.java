package com.used.lux.dto;

import com.used.lux.domain.Auction;
import com.used.lux.domain.Product;
import com.used.lux.domain.State;

import java.time.LocalDateTime;

public record AuctionDto(
        Long id,
        ProductDto productDto,
        StateDto stateDto,
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

    public static AuctionDto of(Long id, ProductDto productDto, StateDto stateDto, int startPrice, int presentPrice, int closingPrice,
                      LocalDateTime auctionStartDate, LocalDateTime auctionClosingDate, int biddingCount,
                      String bidder, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt,
                      String modifiedBy) {
        return new AuctionDto(id, productDto, stateDto, startPrice, presentPrice, closingPrice, auctionStartDate,
                auctionClosingDate, biddingCount, bidder, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static AuctionDto of(int price) {
        return new AuctionDto(null, null, null, 0, price, 0,
                null, null, 0, null, null,
                null, null, null);
    }

    public static AuctionDto from(Auction entity) {
        return new AuctionDto(
                entity.getId(),
                ProductDto.from(entity.getProduct()),
                StateDto.from(entity.getState()),
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

    public Auction toEntity(Product product, State state) {
        return Auction.of(product, state, startPrice, presentPrice, closingPrice, auctionStartDate, auctionClosingDate,
                biddingCount, bidder);
    }

}
