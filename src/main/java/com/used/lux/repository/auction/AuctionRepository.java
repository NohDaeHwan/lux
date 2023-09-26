package com.used.lux.repository.auction;

import com.used.lux.domain.auction.Auction;
import com.used.lux.repository.querydsl.AuctionRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AuctionRepository extends JpaRepository<Auction, Long>, AuctionRepositoryCustom {

    Auction findByStartPrice(int startPrice);

    @Query(value ="SELECT a FROM Auction a WHERE a.prod.prodNm LIKE %:query%")
    List<Auction> findByQuery(@Param("query") String query, Pageable pageable);

    @Query(nativeQuery = true , value = "select * from auction where auc_end_date >= now() ORDER BY auc_end_date asc limit 1 ;")
    Auction findByClosingNearDate();

    @Query(nativeQuery = true , value = "select * from auction where auc_state = 10 and auc_end_date >= :sD and auc_end_date <= now()  order by end_price desc  limit 1 ;")
    Auction findByhighPriceWithState10(@Param("sD") String sectionStartDate);

    @Query(nativeQuery = true ,value = "SELECT * FROM auction WHERE bidding_cnt > 0  AND auc_state = 10 ORDER BY auc_end_date ASC LIMIT 1;")
    Auction findByDateWithFailBid();
    @Query(nativeQuery = true , value = "select * from auction where auc_state = 9 order by end_price desc  limit 1 ;")
    Auction findByHighPriceWithState9();

    @Query(nativeQuery = true , value = "select * from auction where auc_state = 9 order by bidding_cnt desc  limit 1 ;")
    Auction findByMostBiddingWithState9();

    @Query(nativeQuery = true,value = "select sum(end_price) FROM (SELECT * FROM auction WHERE auc_end_date >= :sD AND auc_end_date <= NOW()) AS au WHERE au.auc_state = 10; ")
    Long sumProfitByDate(@Param("sD") String sectionStartDate);


//카테고리 서치
    //쿼리문 추가
    @Query(value ="select a from Auction a where a.prod.prodBrand.brandName Like %:productBrand% AND a.prod.cateM.id =:mcategoryId and " +
            "a.prod.prodColor like %:productColor% and a.prod.prodGender = :productGender " +
            "and a.prod.prodSize like %:productSize% and a.presentPrice between :minPrice and :maxPrice and" +
            " a.prod.prodGrade = :productGrade and a.prod.prodNm LIKE %:query% ")
    List<Auction> searchAuctionBy(Long mcategoryId,String productColor,String productBrand,String productGender,String productSize,String productGrade,int maxPrice, int minPrice,String query);

    @Query(value ="SELECT a FROM Auction a WHERE a.aucState = :auctionState " +
            "AND a.prod.prodNm LIKE %:query%",
            countQuery = "SELECT a FROM Auction a WHERE a.aucState = :auctionState " +
                    "AND a.prod.prodNm LIKE %:query%")
    Page<Auction> findByBackAuctionList(String auctionState, String query, Pageable pageable);

    @Query(value ="SELECT a FROM Auction a WHERE a.aucState = :auctionState " +
            "AND a.prod.prodNm LIKE %:query% " +
            "AND a.aucStartDate >= :auctionDate",
            countQuery = "SELECT a FROM Auction a WHERE a.aucState = :auctionState " +
                    "AND a.prod.prodNm LIKE %:query% " +
                    "AND a.aucStartDate >= :auctionDate")
    Page<Auction> findByBackAuctionListDate(String auctionState, LocalDateTime auctionDate, String query, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM auction WHERE auc_start_date is not null")
    Page<Auction> findByAuctionStartDate(Pageable pageable);

    @Query(nativeQuery = true, value = "select * from auction where auc_state = 10 and auc_start_date < now() order by auc_start_date desc limit 4")
    List<Auction> findByState10AndRecent4List();
    
}
