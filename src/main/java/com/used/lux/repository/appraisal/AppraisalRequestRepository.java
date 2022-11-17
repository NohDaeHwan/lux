package com.used.lux.repository.appraisal;

import com.used.lux.domain.appraisal.AppraisalRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppraisalRequestRepository extends JpaRepository<AppraisalRequest, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM appraisal_request WHERE user_account_id = :id")
    List<AppraisalRequest> findByUserAccountId2(Long id);

    @Query(nativeQuery = true, value = "select count(*) from appraisal_request where appraisal_state_id = 1")
    Long countByState1();

}
