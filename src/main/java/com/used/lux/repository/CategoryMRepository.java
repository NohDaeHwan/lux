package com.used.lux.repository;

import com.used.lux.domain.CategoryM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryMRepository extends JpaRepository<CategoryM, Long> {
    CategoryM findByCategoryMName(String categoryMName);
}
