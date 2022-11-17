package com.used.lux.repository.auction;

import com.used.lux.domain.auction.AuctionLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface AuctionLogRepository extends JpaRepository<AuctionLog, Long> {
    List<AuctionLog> findByBidder(String userName);

    List<AuctionLog> findByProductId(Long productId);

    List<AuctionLog> findByAuctionId(Long auctionId);
}
