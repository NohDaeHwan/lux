package com.used.lux.repository.product;

import com.used.lux.domain.product.Product;
import com.used.lux.repository.querydsl.ProductRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {

    @Query(value ="SELECT p FROM Product p WHERE p.prodNm LIKE %:query% AND p.prodSellType = 'USED' order by p.prodViewCnt desc")
    List<Product> findByQuery(@Param("query") String query);

    /*@Query(value ="SELECT p FROM Product p WHERE p.prodBrand.brandName LIKE %:productBrand% AND " +
            "p.prodColor LIKE %:productColor% AND p.prodGender = :productGender " +
            "AND p.prodSize LIKE %:productSize% AND p.prodNm LIKE %:query% " +
            "AND p.prodGrade = :productGrade AND p.prodState = 'SELL'" +
            "AND p.prodPrice BETWEEN :minPrice AND :maxPrice",
            countQuery = "SELECT count(p) FROM Product p WHERE p.prodBrand.brandName LIKE %:productBrand% AND " +
                    "p.prodColor LIKE %:productColor% AND p.prodGender = :productGender " +
                    "AND p.prodSize LIKE %:productSize% AND p.prodNm LIKE %:query% " +
                    "AND p.prodGrade = :productGrade AND p.prodState = 'SELL' AND p.prodPrice BETWEEN :minPrice AND :maxPrice")
    Page<Product> findByFrontProductList(String productBrand,String productColor, String productGender,
                                         String productSize, String productGrade, int maxPrice, int minPrice,
                                         String query, Pageable pageable);*/

    @Query(nativeQuery = true, value = "select * from product where prod_state = 'SELL' and prod_sell_type = 'USED' order by prod_view_cnt desc limit 4")
    List<Product> findByState6AndRecent4List();
}
