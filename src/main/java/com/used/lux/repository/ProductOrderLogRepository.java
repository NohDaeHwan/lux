package com.used.lux.repository;

import com.used.lux.domain.ProductOrderLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductOrderLogRepository extends JpaRepository<ProductOrderLog, Long> {
    List<ProductOrderLog> findByUserEmail(String userEmail);

    List<ProductOrderLog> findByProductId(Long productId);

    @Query(nativeQuery = true,value = "select sum(product_price) from product_order_log where created_at >=:sD and created_at <=:eD ;")
    Long countPriceByCreatedAt(@Param("sD") String sectionStartDate, @Param("eD") String nowDate);

    @Query(nativeQuery = true,value = "select count(*) from product_order_log where created_at >=:sD and created_at <=:eD ;")
    Long countOrderByCreatedAt(@Param("sD") String sectionStartDate, @Param("eD") String nowDate);
    @Query(nativeQuery = true,value = "select count(*) from product_order_log where product_state_id = 11 ;")
    Long countorderByState11();
}
