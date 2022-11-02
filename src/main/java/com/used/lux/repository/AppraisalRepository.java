package com.used.lux.repository;

import com.used.lux.domain.Appraisal;
import com.used.lux.repository.querydsl.AppraisalRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppraisalRepository extends JpaRepository<Appraisal, Long>, AppraisalRepositoryCustom {
    List<Appraisal> findByUserAccountId(Long id);
}
