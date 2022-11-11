package com.used.lux.repository;

import com.used.lux.domain.Appraisal;
import com.used.lux.domain.AppraisalRequest;
import com.used.lux.repository.querydsl.AppraisalRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppraisalRequestRepository extends JpaRepository<AppraisalRequest, Long> {

    List<AppraisalRequest> findByUserAccountId(Long id);

}
