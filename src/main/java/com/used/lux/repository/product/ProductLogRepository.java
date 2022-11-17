package com.used.lux.repository.product;

import com.used.lux.domain.product.ProductLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductLogRepository extends JpaRepository<ProductLog, Long> {
    List<ProductLog> findByProductIdOrderByCreatedAtDesc(Long productId);
}
