package com.used.lux.repository.querydsl;

import com.used.lux.domain.Auction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuctionRepositoryCustom {

    Page<Auction> searchAuction(String auctionState, String auctionDate, String query, Pageable pageable);

    Page<Auction> findByQuery(String auctionColor, String auctionBrand, String auctionGender, String auctionSize,
                              String auctionGrade, String maxPrice, String minPrice, String query, Pageable pageablee);

    List<Auction> findByCategoryQuery(Long mcategoryId ,String auctionColor, String auctionBrand, String auctionGender, String auctionSize,
                              String auctionGrade, String maxPrice, String minPrice, String query, Pageable pageablee);

}
