package com.used.lux.repository;

import com.used.lux.domain.AuctionLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuctionLogRepository extends JpaRepository<AuctionLog, Long> {
    List<AuctionLog> findByBidder(String userName);

    List<AuctionLog> findByProductId(Long productId);
}
