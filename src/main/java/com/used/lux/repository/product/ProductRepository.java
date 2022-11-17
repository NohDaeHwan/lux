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

    @Query(value ="SELECT p FROM Product p WHERE p.appraisal.appraisalRequest.appraisalProductName LIKE %:query% AND p.productSellType = '중고'")
    List<Product> findByQuery(@Param("query") String query, Pageable pageable);

    @Query(value ="SELECT p FROM Product p WHERE p.appraisal.appraisalRequest.appraisalBrand.brandName LIKE %:productBrand% AND " +
            "p.appraisal.appraisalRequest.appraisalColor LIKE %:productColor% AND p.appraisal.appraisalRequest.appraisalGender LIKE %:productGender% " +
            "AND p.appraisal.appraisalRequest.appraisalSize LIKE %:productSize% AND p.appraisal.appraisalRequest.appraisalProductName LIKE %:query% " +
            "AND p.appraisal.appraisalGrade LIKE %:productGrade% AND p.productSellType = '중고' AND p.state.stateStep = '판매중' " +
            "AND p.productPrice BETWEEN :minPrice AND :maxPrice",
            countQuery = "SELECT p FROM Product p WHERE p.appraisal.appraisalRequest.appraisalBrand.brandName LIKE %:productBrand% AND " +
                    "p.appraisal.appraisalRequest.appraisalColor LIKE %:productColor% AND p.appraisal.appraisalRequest.appraisalGender LIKE %:productGender% " +
                    "AND p.appraisal.appraisalRequest.appraisalSize LIKE %:productSize% AND p.appraisal.appraisalRequest.appraisalProductName LIKE %:query% " +
                    "AND p.productSellType = '중고' AND p.state.stateStep = '판매중' AND p.productPrice BETWEEN :minPrice AND :maxPrice")
    Page<Product> findByFrontProductList(String productColor, String productBrand, String productGender,
                                         String productSize, String productGrade, int maxPrice, int minPrice,
                                         String query, Pageable pageable);

    @Query(value ="SELECT p FROM Product p WHERE p.appraisal.appraisalRequest.appraisalBrand.brandName LIKE %:productBrand% " +
            "AND p.appraisal.appraisalRequest.appraisalGender LIKE %:productGender% AND p.appraisal.appraisalRequest.appraisalProductName LIKE %:query% " +
            "AND p.appraisal.appraisalRequest.appraisalSize LIKE %:productSize% AND p.state.stateStep LIKE %:productState% " +
            "AND p.appraisal.appraisalGrade LIKE %:productGrade% AND p.productSellType LIKE %:productSellType% " +
            "AND p.createdAt >= :productDate",
            countQuery = "SELECT p FROM Product p WHERE p.appraisal.appraisalRequest.appraisalBrand.brandName LIKE %:productBrand% " +
                    "AND p.appraisal.appraisalRequest.appraisalGender LIKE %:productGender% AND p.appraisal.appraisalRequest.appraisalProductName LIKE %:query% " +
                    "AND p.appraisal.appraisalRequest.appraisalSize LIKE %:productSize% AND p.state.stateStep LIKE %:productState% " +
                    "AND p.appraisal.appraisalGrade LIKE %:productGrade% AND p.productSellType LIKE %:productSellType% " +
                    "AND p.createdAt >= :productDate")
    Page<Product> findByBackProductList(String productSellType, String productBrand, String productGender,
                                        String productSize, String productGrade, String productState,
                                        LocalDateTime productDate, String query, Pageable pageable);

}
