package com.used.lux.repository.querydsl;

import com.querydsl.jpa.JPQLQuery;
import com.used.lux.domain.Appraisal;
import com.used.lux.domain.QAppraisal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDateTime;
import java.util.List;

public class AppraisalRepositoryCustomImpl extends QuerydslRepositorySupport implements AppraisalRepositoryCustom {

    public AppraisalRepositoryCustomImpl() {
        super(Appraisal.class);
    }

    @Override
    public Page<Appraisal> searchAppraise(String appraisalState, String appraisalBrand, String appraisalGender,
                                          String appraisalSize, String appraisalGrade, String appraisalDate,
                                          String query, Pageable pageable) {
        QAppraisal appraisal = QAppraisal.appraisal;

        String[] dateResult = appraisalDate.split("-");

        JPQLQuery<Appraisal> queryResult = from(appraisal)
                .select(appraisal)
                .where(appraisal.appraisalRequest.appraisalState.stateStep.like("%"+appraisalState+"%"),
                        appraisal.appraisalRequest.appraisalBrand.brandName.like("%"+appraisalBrand+"%"),
                        appraisal.appraisalRequest.appraisalGender.like("%"+appraisalGender+"%"),
                        appraisal.appraisalRequest.appraisalSize.like("%"+appraisalSize+"%"),
                        appraisal.appraisalRequest.appraisalProductName.like("%"+query+"%"),
                        appraisal.createdAt.after(LocalDateTime.of(Integer.parseInt(dateResult[0]),
                                Integer.parseInt(dateResult[1]), Integer.parseInt(dateResult[2]), 00, 00)));
        long totalCount = queryResult.fetchCount();
        List<Appraisal> results = getQuerydsl().applyPagination(pageable, queryResult).fetch();
        return new PageImpl<Appraisal>(results, pageable, totalCount);
    }

}
