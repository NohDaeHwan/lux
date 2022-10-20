package com.used.lux.repository;

import com.used.lux.domain.CategoryB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Category_BRepository extends JpaRepository<CategoryB,Long> {
    int deleteByCategoryBName(String st);

    boolean existsByCategoryBName(String st);
}
