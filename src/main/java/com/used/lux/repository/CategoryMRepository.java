package com.used.lux.repository;


import com.used.lux.domain.CategoryM;
import com.used.lux.dto.CategoryMDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryMRepository extends JpaRepository<CategoryM, Long> {
    CategoryM findByCategoryMName(String categoryMName);
    boolean existsByCategoryMName(String st);
    void deleteByCategoryMName(String st);

    void deleteAllByCategoryB_Id(Long id);



    //검색용
    List<CategoryM> findAllByCategoryMNameContaining(String st);

    List<String> findAllById(Long categoryId);
}
