package com.used.lux.repository;

import com.used.lux.domain.Appraisal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppraiseRepository extends JpaRepository<Appraisal, Long> {
}
