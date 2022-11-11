package com.used.lux.repository;

import com.used.lux.domain.Appraisal;
import com.used.lux.repository.querydsl.AppraisalRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppraisalRepository extends JpaRepository<Appraisal, Long>, AppraisalRepositoryCustom {

    /*@Query(nativeQuery = true,value = "SELECT ap.appraisal_price FROM appraisal ap JOIN product pr ON ap.id = pr.appraisal_request_id WHERE pr.id = :id")
    Long findAppraisePriceByProductId(@Param("id") Long aLong);*/

}
