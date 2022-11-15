package com.used.lux.repository;


import com.used.lux.domain.CategoryM;
import com.used.lux.dto.CategoryMDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryMRepository extends JpaRepository<CategoryM, Long> {
    CategoryM findByCategoryMName(String categoryMName);
    boolean existsByCategoryMName(String st);
    void deleteByCategoryMName(String st);

    void deleteAllByCategoryB_Id(Long id);

    List<String> findAllByCategoryB_Id(Long categoryId);

    @Query(nativeQuery = true, value = "SELECT * FROM category_m LIMIT 1")
    CategoryM findByOneCategory();
}
