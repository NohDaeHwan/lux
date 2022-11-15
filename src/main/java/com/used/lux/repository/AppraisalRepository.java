package com.used.lux.repository;

import com.used.lux.domain.Appraisal;
import com.used.lux.domain.AppraisalRequest;
import com.used.lux.repository.querydsl.AppraisalRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.stream.DoubleStream;

public interface AppraisalRepository extends JpaRepository<Appraisal, Long>, AppraisalRepositoryCustom {

    @Query(value = "select a from Appraisal a where a.appraisalRequest.userAccount.id=:id")
    Page<Appraisal> findByUserAccountId(Long id, Pageable pageable);
    /*
     * @Query(nativeQuery = true,value =
     * "SELECT ap.appraisal_price FROM appraisal ap JOIN product pr ON ap.id = pr.appraisal_request_id WHERE pr.id = :id"
     * )
     * public interface AppraisalRepository extends JpaRepository<Appraisal, Long>,
     * AppraisalRepositoryCustom {
     * 
     * @Query(value =
     * "SELECT a FROM Appraisal a WHERE a.appraisalRequest.userAccount.id = :id")
     * Page<Appraisal> findByUserAccountId(Long id, Pageable pageable);
     * 
     * /*@Query(nativeQuery = true,value =
     * "SELECT ap.appraisal_price FROM appraisal ap JOIN product pr ON ap.id = pr.appraisal_id WHERE pr.id = :id"
     * )
     * Long findAppraisePriceByProductId(@Param("id") Long aLong);
     */

}
