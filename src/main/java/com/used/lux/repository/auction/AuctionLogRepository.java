package com.used.lux.repository.auction;

import com.used.lux.domain.auction.AuctionLog;
import com.used.lux.dto.user.auction.AuctionMypageLogDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuctionLogRepository extends JpaRepository<AuctionLog, Long> {

    @Query("SELECT a FROM AuctionLog a WHERE a.userId = :id")
    List<AuctionLog> findByAllId(Long id);

    List<AuctionLog> findByAucId(Long aucId);

    //마이페이지 경매 내역 조회
    @Query(value="SELECT new com.used.lux.dto.user.auction.AuctionMypageLogDto(al.id, al.userId, a.userId, al.aucId, al.prodNm, al.presentPrice, " +
            "a.endPrice, al.createdAt, al.createdBy, al.modifiedAt, al.modifiedBy) " +
            "FROM AuctionLog al INNER JOIN Auction a ON al.aucId = a.id " +
            "WHERE al.userId = :userId")
    Page<AuctionMypageLogDto> findByUserId(Long userId, Pageable pageable);
}
