package com.used.lux.repository.querydsl;

import com.used.lux.domain.AppraisalRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AppraisalRequestRepositoryCustom {

    Page<AppraisalRequest> searchAppraise(String appraisalState, String appraisalBrand, String appraisalGender,
                                          String appraisalSize, String appraisalGrade, String appraisalDate,
                                          String query, Pageable pageable);
}
