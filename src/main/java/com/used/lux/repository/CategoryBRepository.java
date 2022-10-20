package com.used.lux.repository;

import com.used.lux.domain.CategoryB;
import com.used.lux.domain.CategoryM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryBRepository extends JpaRepository<CategoryB, Long> {
    CategoryB findByCategoryBName(String categoryBName);
}
