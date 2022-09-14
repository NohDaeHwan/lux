package com.used.lux.repository;

import com.used.lux.domain.AuctionOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionOrderRepository extends JpaRepository<AuctionOrder,Long> {
}
