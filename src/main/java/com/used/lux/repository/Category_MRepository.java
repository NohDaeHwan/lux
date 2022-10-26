package com.used.lux.repository;

import com.used.lux.domain.CategoryM;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Category_MRepository extends JpaRepository<CategoryM,Long> {
    boolean existsByCategoryMName(String st);
    void deleteByCategoryMName(String st);

    void deleteAllByCategoryB(String st);

    List<String> findAllByCategoryMName(String st);
}
