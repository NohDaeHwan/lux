package com.used.lux.repository;

import com.used.lux.domain.Appraisal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppraisalRepository extends JpaRepository<Appraisal, Long> {
    List<Appraisal> findByUserAccountId(Long id);
}
