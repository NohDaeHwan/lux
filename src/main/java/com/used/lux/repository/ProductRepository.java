package com.used.lux.repository;

import com.used.lux.domain.Product;
import com.used.lux.repository.querydsl.ProductRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom{
}
