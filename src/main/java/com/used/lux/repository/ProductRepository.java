package com.used.lux.repository;

import com.used.lux.domain.Product;
import com.used.lux.repository.querydsl.ProductRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom{

    @Query(value ="SELECT p FROM Product p WHERE p.appraisalRequest.appraisalProductName LIKE %:query% AND p.productSellType = '중고'")
    List<Product> findByQuery(@Param("query") String query, Pageable pageable);

}
