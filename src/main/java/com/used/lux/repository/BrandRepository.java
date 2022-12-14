package com.used.lux.repository;

import com.used.lux.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    boolean existsByBrandName(String st);
    int deleteByBrandName(String st);
    Brand findByBrandName(String brandName);
}
