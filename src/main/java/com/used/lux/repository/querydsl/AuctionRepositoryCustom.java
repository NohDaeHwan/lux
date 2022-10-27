package com.used.lux.repository.querydsl;

import com.used.lux.domain.Auction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuctionRepositoryCustom {

    Page<Auction> searchAuction(String auctionState, String auctionDate, String query, Pageable pageable);

}
