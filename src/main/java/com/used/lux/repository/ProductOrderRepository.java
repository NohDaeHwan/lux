package com.used.lux.repository;

import com.used.lux.domain.ProductOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductOrderRepository extends JpaRepository<ProductOrder,Long> {

    @Query(nativeQuery = true,value ="select * from product_order where user_account_id=:id order by id",
            countQuery="select count(*) from product_order where user_account_id=:id")
    Page<ProductOrder> findByProductId(@Param("id") Long id, Pageable pageable);

}
