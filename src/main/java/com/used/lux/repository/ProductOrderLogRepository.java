package com.used.lux.repository;

import com.used.lux.domain.ProductOrderLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ProductOrderLogRepository extends JpaRepository<ProductOrderLog, Long> {
    List<ProductOrderLog> findByUserEmail(String userEmail);

    List<ProductOrderLog> findByProductId(Long productId);

    @Query(nativeQuery = true,value = "select sum(product_price) from product_order_log where created_at >=:sD and created_at <= now() ;")
    Long countPriceByCreatedAt(@Param("sD") String sectionStartDate);

    @Query(nativeQuery = true,value = "select count(*) from product_order_log where created_at >=:sD and created_at <= now() ;")
    Long countOrderByCreatedAt(@Param("sD") String sectionStartDate);

    @Query(nativeQuery = true,value = "select count(*) from product_order_log where product_state_id = 11 ;")
    Long countOrderByState11();

    @Query(nativeQuery = true,value = "SELECT s.state_name  from " +
            "(SELECT * FROM product_order_log il WHERE il.product_state_id = 6 OR il.product_state_id = 10) as l  " +
            "JOIN state s ON l.product_state_id = s.id  " +
            "WHERE l.created_at >= :sD and l.created_at <= now() GROUP BY l.product_state_id order by count(*) DESC LIMIT 1;")
    String findBySellTypeOfState(@Param("sD") String sectionStartDate);

    @Query(nativeQuery = true,value = "SELECT p.category_b_id " +
            "FROM product_order_log l JOIN  product p ON l.product_id = p.id  " +
            "WHERE l.created_at <= NOW() AND l.created_at >= :sD " +
            "GROUP BY p.category_b_id " +
            "ORDER BY COUNT(*) DESC LIMIT 1; ")
    String findBySellTypeOfCategoryB(@Param("sD") String sectionStartDate);

    @Query(nativeQuery = true,value = "SELECT p.category_m_id  FROM product_order_log l JOIN  product p ON l.product_id = p.id  " +
            "WHERE l.created_at <= NOW() AND l.created_at >= :sD " +
            "GROUP BY p.category_m_id ORDER BY COUNT(*) DESC LIMIT 1;")
    String findBySellTypeOfCategoryM(@Param("sD") String sectionStartDate);

    @Query(nativeQuery = true,value = "SELECT TRUNCATE(product_price,-5) AS pricegroup " +
            "FROM product_order_log " +
            "WHERE created_at >= :sD AND created_at <= NOW() " +
            "GROUP BY pricegroup ORDER BY COUNT(*) desc LIMIT 1;")
    String findByPriceRange(@Param("sD") String sectionStartDate);

    @Query (nativeQuery = true,value = "SELECT p.appraisal_id " +
            "FROM (SELECT * FROM product_order_log il WHERE  il.product_state_id = 9 OR il.product_state_id = 5) as l " +
            "JOIN product p ON l.product_id = p.id " +
            "WHERE l.created_at >= :sD AND l.created_at <= NOW() " +
            "ORDER BY p.product_view_count DESC LIMIT 1; ")
    String findByMostViewCount(@Param("sD") String sectionStartDate);

    @Query(nativeQuery = true,value = "select sum(product_price) " +
            "FROM (SELECT * FROM product_order_log WHERE created_at >= :sD AND created_at <= NOW()) AS pl" +
            " WHERE pl.product_state_id = 6; ")
    Long sumProfitByDate(@Param("sD") String sectionStartDate);

    @Query(nativeQuery = true,value = "select pl.product_id " +
            "FROM (SELECT  * FROM product_order_log WHERE created_at >= :sD AND created_at <= NOW()) AS pl" +
            " WHERE pl.product_state_id = 6; ")
    List<Long> profitIdByDate(@Param("sD") String sectionStartDate);

    @Query(nativeQuery = true,value = "select count(*) from product_order_log where product_state_id = 5")
    Long countSellingProduct();
    @Query(nativeQuery = true,value = "select count(*) from product_order_log where product_state_id = 9")
    Long countProgressAuction();
}
