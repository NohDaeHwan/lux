package com.used.lux.repository;

import com.used.lux.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsedluxRepository extends JpaRepository<Product, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM product WHERE brand_name LIKE '%:brandName%' AND "
            + "big_category LIKE '%:bigCategory%' AND small_category LIKE '%:smallCategory%' AND gender LIKE '%:gender%' AND "
            + "state LIKE '%:state%' AND size LIKE '%:size%' AND product_name LIKE '%:productName%' "
            + "ORDER BY created_at DESC, product_name",
            countQuery = "SELECT count(*) FROM product WHERE brand_name LIKE '%:brandName%' AND "
                    + "big_category LIKE '%:bigCategory%' AND small_category LIKE '%:smallCategory%' AND gender LIKE '%:gender%' AND "
                    + "state LIKE '%:state%' AND size LIKE '%:size%' AND product_name LIKE '%:productName%'")
    Page<Product> findByQuery(@Param("brandName") String brandName, @Param("bigCategory") String bigCategory,
                              @Param("smallCategory") String smallCategory, @Param("gender") String gender, @Param("state") String state,
                              @Param("size") String size, @Param("productName") String productName, Pageable pageable);

}
