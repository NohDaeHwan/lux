package com.used.lux.repository;

import com.used.lux.domain.Auction;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;

public interface AuctionRepository extends JpaRepository<Auction, Long> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE auction SET bidder = :nickName, present_price = :price, bidding_count = bidding_count + 1 WHERE id = :auctionId")
    Integer updateAuction(@Param("auctionId") Long auctionId, @Param("price") int price, @Param("nickName") String nickName);

    @Query(nativeQuery = true, value ="SELECT * FROM auction WHERE closing_price = 0 ORDER BY created_at",
            countQuery="SELECT count(*) FROM auction WHERE closing_price = 0")
    Page<Auction> findList(Pageable pageable);

    @Query(nativeQuery = true, value ="SELECT * FROM auction WHERE closing_price != 0 ORDER BY created_at",
            countQuery="SELECT count(*) FROM auction WHERE closing_price != 0")
    Page<Auction> findResult(Pageable pageable);

}
