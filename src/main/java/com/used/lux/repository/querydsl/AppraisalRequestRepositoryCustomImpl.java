package com.used.lux.repository.querydsl;

import com.querydsl.jpa.JPQLQuery;
import com.used.lux.domain.Appraisal;
import com.used.lux.domain.AppraisalRequest;
import com.used.lux.domain.QAppraisal;
import com.used.lux.domain.QAppraisalRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDateTime;
import java.util.List;

public class AppraisalRequestRepositoryCustomImpl extends QuerydslRepositorySupport implements AppraisalRequestRepositoryCustom {

    public AppraisalRequestRepositoryCustomImpl() {
        super(AppraisalRequest.class);
    }

    @Override
    public Page<AppraisalRequest> searchAppraise(String appraisalState, String appraisalBrand, String appraisalGender,
                                                 String appraisalSize, String appraisalGrade, String appraisalDate,
                                                 String query, Pageable pageable) {
        QAppraisalRequest appraisalRequest = QAppraisalRequest.appraisalRequest;

        String[] dateResult = appraisalDate.split("-");

        JPQLQuery<AppraisalRequest> queryResult = from(appraisalRequest)
                .select(appraisalRequest)
                .where(appraisalRequest.appraisalState.stateStep.like("%"+appraisalState+"%"),
                        appraisalRequest.appraisalBrand.brandName.like("%"+appraisalBrand+"%"),
                        appraisalRequest.appraisalGender.like("%"+appraisalGender+"%"),
                        appraisalRequest.appraisalSize.like("%"+appraisalSize+"%"),
                        appraisalRequest.appraisalProductName.like("%"+query+"%"),
                        appraisalRequest.createdAt.after(LocalDateTime.of(Integer.parseInt(dateResult[0]),
                                Integer.parseInt(dateResult[1]), Integer.parseInt(dateResult[2]), 00, 00)));
        long totalCount = queryResult.fetchCount();
        List<AppraisalRequest> results = getQuerydsl().applyPagination(pageable, queryResult).fetch();
        return new PageImpl<AppraisalRequest>(results, pageable, totalCount);
    }

}
