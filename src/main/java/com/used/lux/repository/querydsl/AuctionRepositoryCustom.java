package com.used.lux.repository.querydsl;

import com.used.lux.domain.auction.Auction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface AuctionRepositoryCustom {

    Page<Auction> searchAuction(String auctionState, String auctionDate, String query, Pageable pageable);

    Page<Auction> findByQuery(String auctionColor, String auctionBrand, String auctionGender, String auctionSize,
                              String auctionGrade, String maxPrice, String minPrice, String query, Pageable pageablee);

    List<Auction> findByCateQuery(Long mcategoryId ,String auctionColor, String auctionBrand, String auctionGender, String auctionSize,
                              String auctionGrade, long maxPrice, long minPrice, String query);

    Page<Auction> findByBackAucList(String auctionState, String auctionDate, String query, Pageable pageable);

}
