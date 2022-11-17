package com.used.lux.repository.querydsl;

import com.used.lux.domain.appraisal.Appraisal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AppraisalRepositoryCustom {

    Page<Appraisal> searchAppraise(String appraisalState, String appraisalBrand, String appraisalGender,
                                   String appraisalSize, String appraisalGrade, String appraisalDate,
                                   String query, Pageable pageable);
}
