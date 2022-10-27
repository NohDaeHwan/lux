package com.used.lux.repository.querydsl;

import com.used.lux.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {

    Page<Product> searchProduct(String productSellType, String productBrand, String productGender,
                                String productSize, String productGrade, String productState,
                                String productDate, String query, Pageable pageable);

    Page<Product> findByQuery(String brandName, String categoryBId, String categoryMId, String gender,
                              String state, String size, String productName, Pageable pageable);
}
