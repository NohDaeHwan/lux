package com.used.lux.repository;

import com.used.lux.domain.ProductOrderCancel;
import com.used.lux.request.OrderCancelRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOrderCancelRepository extends JpaRepository<ProductOrderCancel, Long> {
    List<ProductOrderCancel> findByUserName(String userEmail);
    ProductOrderCancel findByOrderId(Long orderId);
}
