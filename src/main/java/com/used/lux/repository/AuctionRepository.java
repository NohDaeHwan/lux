package com.used.lux.repository;

import com.used.lux.domain.Auction;
import com.used.lux.repository.querydsl.AuctionRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface AuctionRepository extends JpaRepository<Auction, Long>, AuctionRepositoryCustom {

    @Query(nativeQuery = true, value ="SELECT * FROM auction WHERE closing_price != 0 ORDER BY created_at",
            countQuery="SELECT count(*) FROM auction WHERE closing_price != 0")
    Page<Auction> findResult(Pageable pageable);

    Auction findByStartPrice(int startPrice);

    @Query(value ="SELECT a FROM Auction a WHERE a.product.appraisal.appraisalRequest.appraisalProductName LIKE %:query%")
    List<Auction> findByQuery(@Param("query") String query, Pageable pageable);

    Auction findByauctionClosingDate(LocalDateTime auctionClosingDate);

    @Query(nativeQuery = true , value = "select * from auction where auction_closing_date >= now() ORDER BY auction_closing_date asc limit 1 ;")
    Auction findByClosingNearDate();

    @Query(nativeQuery = true , value = "select * from auction where state_id = 10 and auction_closing_date >= :sD and auction_closing_date <= now()  order by closing_price desc  limit 1 ;")
    Auction findByhighPriceWithState10(@Param("sD") String sectionStartDate);

    @Query(nativeQuery = true ,value = "SELECT * FROM auction WHERE bidding_count>0  AND state_id = 10 ORDER BY auction_closing_date ASC LIMIT 1;")
    Auction findByDateWithFailBid();
    @Query(nativeQuery = true , value = "select * from auction where state_id = 9 order by closing_price desc  limit 1 ;")
    Auction findByHighPriceWithState9();

    @Query(nativeQuery = true , value = "select * from auction where state_id = 9 order by bidding_count desc  limit 1 ;")
    Auction findByMostBiddingWithState9();

    @Query(nativeQuery = true,value = "select sum(closing_price) FROM (SELECT * FROM auction WHERE auction_closing_date >= :sD AND auction_closing_date <= NOW()) AS au WHERE au.state_id = 10; ")
    Long sumProfitByDate(@Param("sD") String sectionStartDate);


//카테고리 서치
    @Query(value ="select a from Auction a where a.product.appraisal.appraisalRequest.appraisalBrand.brandName Like %:productBrand% AND a.product.categoryM.id =:mcategoryId and " +
            "a.product.appraisal.appraisalRequest.appraisalColor like %:productColor% and a.product.appraisal.appraisalRequest.appraisalGender like %:productGender% " +
            "and a.product.appraisal.appraisalRequest.appraisalSize like %:productSize% and a.presentPrice between :minPrice and :maxPrice" )
    List<Auction> searchAuctionBy(@Param("productBrand") String brandName,Long mcategoryId, String productColor,String productGender,String productSize, int maxPrice,int minPrice);



    void deleteByProductId(Long id);

    Auction findByProductId(Long id);
}
