package com.used.lux.repository;

import com.used.lux.domain.CategoryM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryMRepository extends JpaRepository<CategoryM, Long> {
    CategoryM findByCateMNm(String categoryMName);
    boolean existsByCateMNm(String st);
    void deleteByCateMNm(String st);

    void deleteAllByCateB_Id(Long id);

    List<String> findAllByCateB_Id(Long categoryId);

    @Query(nativeQuery = true, value = "SELECT * FROM category_m LIMIT 1")
    CategoryM findByOneCategory();

    List<CategoryM> findByCateB_Id(Long cateBId);
}
