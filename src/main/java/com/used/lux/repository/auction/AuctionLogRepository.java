package com.used.lux.repository.auction;

import com.querydsl.core.group.GroupBy;
import com.used.lux.domain.auction.AuctionLog;
import com.used.lux.dto.user.auction.AuctionLogDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface AuctionLogRepository extends JpaRepository<AuctionLog, Long> {

    @Query(nativeQuery = true, value ="SELECT * FROM auction_log WHERE bidder = :userName")
    List<AuctionLog> findByBidderList(String userName);

    List<AuctionLog> findByProductId(Long productId);

    List<AuctionLog> findByAuctionId(Long auctionId);

    //마이페이지 경매 내역 조회
    Page<AuctionLog> findByBidder(String bidder, Pageable pageable);
}
