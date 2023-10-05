package com.used.lux.repository.order;

import com.used.lux.domain.order.ProductOrderLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ProductOrderLogRepository extends JpaRepository<ProductOrderLog, Long> {

    List<ProductOrderLog> findByProdId(Long prodId);

    ProductOrderLog findByProdIdAndUserId(Long prodId, Long userId);
    
    @Query(nativeQuery = true,value = "select sum(order_price) from product_order_log where created_at >=:sD and created_at <= now();")
    Long countPriceByCreatedAt(@Param("sD") String sectionStartDate);

    @Query(nativeQuery = true,value = "select count(*) from product_order_log where created_at >=:sD and created_at <= now();")
    Long countOrderByCreatedAt(@Param("sD") String sectionStartDate);

    @Query(nativeQuery = true,value = "select count(*) from product_order_log where prod_state = 'COMPLETE';")
    Long countOrderByState11();

    @Query(nativeQuery = true,value = "SELECT l.prod_sell_type from product_order_log l " +
            "WHERE l.created_at >= :sD and l.created_at <= now() GROUP BY l.prod_sell_type order by count(*) DESC LIMIT 1;")
    String findBySellTypeOfState(@Param("sD") String sectionStartDate);

    @Query(nativeQuery = true,value = "SELECT p.cate_b_id " +
            "FROM product_order_log l JOIN  product p ON l.prod_id = p.id  " +
            "WHERE l.created_at <= NOW() AND l.created_at >= :sD " +
            "GROUP BY p.cate_b_id " +
            "ORDER BY COUNT(*) DESC LIMIT 1; ")
    String findBySellTypeOfCategoryB(@Param("sD") String sectionStartDate);

    @Query(nativeQuery = true,value = "SELECT p.cate_m_id  FROM product_order_log l JOIN  product p ON l.prod_id = p.id  " +
            "WHERE l.created_at <= NOW() AND l.created_at >= :sD " +
            "GROUP BY p.cate_m_id ORDER BY COUNT(*) DESC LIMIT 1;")
    String findBySellTypeOfCategoryM(@Param("sD") String sectionStartDate);

    @Query(nativeQuery = true,value = "SELECT TRUNCATE(order_price,-5) AS pricegroup " +
            "FROM product_order_log " +
            "WHERE created_at >= :sD AND created_at <= NOW() " +
            "GROUP BY pricegroup ORDER BY COUNT(*) desc LIMIT 1;")
    String findByPriceRange(@Param("sD") String sectionStartDate);

    @Query (nativeQuery = true,value = "SELECT p.id " +
            "FROM (SELECT * FROM product_order_log il WHERE  il.prod_state = 9 OR il.prod_state = 5) as l " +
            "JOIN product p ON l.prod_id = p.id " +
            "WHERE l.created_at >= :sD AND l.created_at <= NOW() " +
            "ORDER BY p.prod_view_cnt DESC LIMIT 1; ")
    String findByMostViewCount(@Param("sD") String sectionStartDate);

    @Query(nativeQuery = true,value = "select sum(order_price) " +
            "FROM (SELECT * FROM product_order_log WHERE created_at >= :sD AND created_at <= NOW()) AS pl" +
            " WHERE pl.prod_state = 6; ")
    Long sumProfitByDate(@Param("sD") String sectionStartDate);

    @Query(nativeQuery = true,value = "select pl.prod_id " +
            "FROM (SELECT  * FROM product_order_log WHERE created_at >= :sD AND created_at <= NOW()) AS pl" +
            " WHERE pl.prod_state = 6; ")
    List<Long> profitIdByDate(@Param("sD") String sectionStartDate);

    @Query(nativeQuery = true,value = "select count(*) from product_order_log where prod_state = 5")
    Long countSellingProduct();
    @Query(nativeQuery = true,value = "select count(*) from product_order_log where prod_state = 9")
    Long countProgressAuction();
}
