package com.used.lux.repository;

import com.used.lux.domain.Auction;
import com.used.lux.repository.querydsl.AuctionRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface AuctionRepository extends JpaRepository<Auction, Long>, AuctionRepositoryCustom {

    @Query(nativeQuery = true, value ="SELECT * FROM auction WHERE closing_price = 0 ORDER BY created_at",
            countQuery="SELECT count(*) FROM auction WHERE closing_price = 0")
    Page<Auction> findList(Pageable pageable);

    @Query(nativeQuery = true, value ="SELECT * FROM auction WHERE closing_price != 0 ORDER BY created_at",
            countQuery="SELECT count(*) FROM auction WHERE closing_price != 0")
    Page<Auction> findResult(Pageable pageable);

    Auction findByStartPrice(int startPrice);

    Auction findByauctionStartDate(LocalDateTime auctionStartDate);

    Auction findByauctionClosingDate(LocalDateTime auctionClosingDate);

    @Query(nativeQuery = true , value = "select * from auction where auction_closing_date >= now() ORDER BY auction_closing_date asc limit 1 ;")
    Auction findByClosingNearDate();

    @Query(nativeQuery = true , value = "select * from auction where state_id = 10 order by closing_price desc  limit 1 ;")
    Auction findByhighPriceWithState10(String toString, String toString1);

    @Query(nativeQuery = true ,value = "SELECT * FROM auction WHERE bidding_count>0  AND state_id = 10 ORDER BY auction_closing_date ASC LIMIT 1;")
    Auction findByDateWithFailBid();
    @Query(nativeQuery = true , value = "select * from auction where state_id = 9 order by closing_price desc  limit 1 ;")
    Auction findByHighPriceWithState9();

    @Query(nativeQuery = true , value = "select * from auction where state_id = 9 order by bidding_count desc  limit 1 ;")
    Auction findByMostBiddingWithState9();
}
