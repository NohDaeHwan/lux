package com.used.lux.repository;

import com.used.lux.domain.ProductLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductLogRepository extends JpaRepository<ProductLog, Long> {
}
