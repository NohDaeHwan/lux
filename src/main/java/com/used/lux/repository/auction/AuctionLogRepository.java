package com.used.lux.repository.auction;

import com.used.lux.domain.auction.AuctionLog;
import com.used.lux.dto.user.auction.AuctionMypageLogDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuctionLogRepository extends JpaRepository<AuctionLog, Long> {

    @Query(nativeQuery = true, value ="SELECT * FROM auction_log WHERE bidder = :userName")
    List<AuctionLog> findByBidderList(String userName);

    List<AuctionLog> findByProductId(Long productId);

    List<AuctionLog> findByAuctionId(Long auctionId);

    //마이페이지 경매 내역 조회
    @Query(value="SELECT new com.used.lux.dto.user.auction.AuctionMypageLogDto(al.id, al.bidder, a.bidder, al.auctionId, al.productId, al.productName, al.presentPrice, " +
            "a.endPrice, al.createdAt, al.createdBy, al.modifiedAt, al.modifiedBy) " +
            "FROM AuctionLog al INNER JOIN Auction a ON al.auctionId = a.id " +
            "WHERE al.bidder = :bidder")
    Page<AuctionMypageLogDto> findByBidder(String bidder, Pageable pageable);
}
