package com.used.lux.repository.order;

import com.used.lux.domain.order.ProductOrderCancel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOrderCancelRepository extends JpaRepository<ProductOrderCancel, Long> {
    List<ProductOrderCancel> findByUserName(String userEmail);
    ProductOrderCancel findByOrderId(Long orderId);
}
