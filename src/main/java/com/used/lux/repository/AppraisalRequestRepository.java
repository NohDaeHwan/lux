package com.used.lux.repository;

import com.used.lux.domain.AppraisalRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppraisalRequestRepository extends JpaRepository<AppraisalRequest, Long> {

    Page<AppraisalRequest> findByUserAccountId(Long id, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM appraisal_request WHERE user_account_id = :id")
    List<AppraisalRequest> findByUserAccountId2(Long id);
}
