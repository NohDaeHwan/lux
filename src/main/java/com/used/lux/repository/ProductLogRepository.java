package com.used.lux.repository;

import com.used.lux.domain.ProductLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductLogRepository extends JpaRepository<ProductLog, Long> {
    List<ProductLog> findByProductIdOrderByCreatedAtDesc(Long productId);
}
