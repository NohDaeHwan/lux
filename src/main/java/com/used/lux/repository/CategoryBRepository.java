package com.used.lux.repository;

import com.used.lux.domain.CategoryB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryBRepository extends JpaRepository<CategoryB, Long> {
    CategoryB findByCateBNm(String categoryBName);
    
    int deleteByCateBNm(String st);

    boolean existsByCateBNm(String st);

    //검색용
    List<CategoryB> findAllByCateBNmContaining(String st);

    @Query(nativeQuery = true, value = "SELECT * FROM category_b LIMIT 1")
    CategoryB findByOneCategory();
}
