package com.used.lux.repository;

import com.used.lux.domain.Auction;
import com.used.lux.repository.querydsl.AuctionRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuctionRepository extends JpaRepository<Auction, Long>, AuctionRepositoryCustom {

    @Query(nativeQuery = true, value ="SELECT * FROM auction WHERE closing_price != 0 ORDER BY created_at",
            countQuery="SELECT count(*) FROM auction WHERE closing_price != 0")
    Page<Auction> findResult(Pageable pageable);

    Auction findByStartPrice(int startPrice);

    @Query(value ="SELECT a FROM Auction a WHERE a.product.appraisalRequest.appraisalProductName LIKE %:query%")
    List<Auction> findByQuery(@Param("query") String query, Pageable pageable);

}
