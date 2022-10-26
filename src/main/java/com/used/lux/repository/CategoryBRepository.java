package com.used.lux.repository;

import com.used.lux.domain.CategoryB;
import com.used.lux.dto.CategoryBDto;
import com.used.lux.request.CategoryCreateRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryBRepository extends JpaRepository<CategoryB, Long> {
    CategoryB findByCategoryBName(String categoryBName);
    
    int deleteByCategoryBName(String st);

    boolean existsByCategoryBName(String st);
    
    //검색용
    List<CategoryB> findAllByCategoryBNameContaining(String st);
}
