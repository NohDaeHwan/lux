package com.used.lux.repository;

import com.used.lux.domain.ForAppraisal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppraiseRepository extends JpaRepository<ForAppraisal, Long> {
}
