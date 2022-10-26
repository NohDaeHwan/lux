package com.used.lux.repository;

import com.used.lux.domain.ProductOrderCancel;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

public interface ProductOrderCancelRepository extends JpaRepository<ProductOrderCancel, Long> {
    List<ProductOrderCancel> findByUserName(String userEmail);

    @Nullable
    ProductOrderCancel findByOrderCancelId(Long orderId);

}
