package com.used.lux.repository.appraisal;

import com.used.lux.domain.appraisal.Appraisal;
import com.used.lux.repository.querydsl.AppraisalRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppraisalRepository extends JpaRepository<Appraisal, Long>, AppraisalRepositoryCustom {

    @Query("SELECT a FROM Appraisal a WHERE a.userAccount.id = :id")
    List<Appraisal> findByUserAccountId2(Long id);

    @Query("select count(a) from Appraisal a where a.appState = '1'")
    Long countByState1();

    @Query(value = "select a from Appraisal a where a.userAccount.id=:id")
    Page<Appraisal> findByUserAccountId(Long id, Pageable pageable);

}
