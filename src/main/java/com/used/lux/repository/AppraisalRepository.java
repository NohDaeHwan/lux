package com.used.lux.repository;

import com.used.lux.domain.Appraisal;
import com.used.lux.repository.querydsl.AppraisalRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppraisalRepository extends JpaRepository<Appraisal, Long>, AppraisalRepositoryCustom {
    List<Appraisal> findByUserAccountId(Long id);
    @Query(nativeQuery = true,value = "select count(*) from appraisal where appraisal_state = 1 ;")
    Long countByState1();
}
