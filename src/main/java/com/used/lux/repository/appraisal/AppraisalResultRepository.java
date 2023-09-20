package com.used.lux.repository.appraisal;

import com.used.lux.domain.appraisal.AppraisalResult;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppraisalResultRepository extends JpaRepository<AppraisalResult, Long> {

//    @Query(value ="SELECT p FROM AppraisalResult p  WHERE p.appraisalRequest.appraisalProductName LIKE %:query%")
//    List<AppraisalResult> findByQuery(@Param("query") String query);

}
