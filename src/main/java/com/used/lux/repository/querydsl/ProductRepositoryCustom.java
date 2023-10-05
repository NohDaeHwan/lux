package com.used.lux.repository.querydsl;

import com.used.lux.domain.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepositoryCustom {

    Page<Product> searchProduct(String productSellType, String productBrand, String productGender,
                               String productSize, String productGrade, String productState,
                               String productDate, String query, Pageable pageable);

    Page<Product> findByQuery(String productColor, String productBrand, String productGender, String productSize,
                              String productGrade, String maxPrice, String minPrice, String query, Pageable pageable);

    List<Product> findByCateQuery(Long mcategoryId, String productColor, String productBrand, String productGender, String productSize,
                                      String productGrade, long maxPrice, long minPrice, String query);

    Page<Product> findByBackProductList(String productBrand, String productGender, String productSize, String productGrade, String productState,
                                        LocalDateTime productDate, String query, Pageable pageable);

    Page<Product> findByFrontProductList(String productBrand,String productColor, String productGender,
                                         String productSize, String productGrade, int maxPrice, int minPrice,
                                         String query, Pageable pageable);
}
