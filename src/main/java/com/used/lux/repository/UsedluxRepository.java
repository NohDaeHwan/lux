package com.used.lux.repository;

import com.used.lux.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsedluxRepository extends JpaRepository<Product, Long> {
}
