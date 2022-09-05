package com.used.lux.dto;

import com.used.lux.domain.Auction;
import com.used.lux.domain.Image;

import java.time.LocalDateTime;

public record AuctionDto(
        Long id,
        ImageDto imageDto,
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
        String bidder,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static AuctionDto of(Long id, ImageDto imageDto, String productName, String brandName, String bigCategory,
                                String smallCategory, int size, String gender, String state, int price, int startPrice,
                                int presentPrice, int closingPrice, LocalDateTime auctionStartDate, LocalDateTime auctionClosingDate,
                                int viewCount, int biddingCount, String bidder, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt,
                                String modifiedBy) {
        return new AuctionDto(id, imageDto, productName, brandName, bigCategory, smallCategory, size, gender, state,
                price, startPrice, presentPrice, closingPrice, auctionStartDate, auctionClosingDate, viewCount,
                biddingCount, bidder, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static AuctionDto from(Auction entity) {
        return new AuctionDto(
                entity.getId(),
                ImageDto.from(entity.getImage()),
                entity.getProductName(),
                entity.getBrandName(),
                entity.getBigCategory(),
                entity.getSmallCategory(),
                entity.getSize(),
                entity.getGender(),
                entity.getState(),
                entity.getPrice(),
                entity.getStartPrice(),
                entity.getPresentPrice(),
                entity.getClosingPrice(),
                entity.getAuctionStartDate(),
                entity.getAuctionClosingDate(),
                entity.getViewCount(),
                entity.getBiddingCount(),
                entity.getBidder(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public Auction toEntity() {
        return Auction.of(productName, brandName, bigCategory, smallCategory, size, gender, state, price, startPrice, presentPrice,
                closingPrice, auctionStartDate, auctionClosingDate, viewCount, biddingCount, bidder, imageDto.toEntity());
    }
}
