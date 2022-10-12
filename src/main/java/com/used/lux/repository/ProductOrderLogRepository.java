package com.used.lux.repository;

import com.used.lux.domain.ProductOrderLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOrderLogRepository extends JpaRepository<ProductOrderLog, Long> {
    List<ProductOrderLog> findByUserEmail(String userEmail);
}
