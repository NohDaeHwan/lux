package com.used.lux.repository;

import com.used.lux.domain.CategoryB;
import com.used.lux.domain.CategoryM;
import com.used.lux.dto.CategoryBDto;
import com.used.lux.request.CategoryCreateRequest;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryBRepository extends JpaRepository<CategoryB, Long> {
    CategoryB findByCategoryBName(String categoryBName);
}
