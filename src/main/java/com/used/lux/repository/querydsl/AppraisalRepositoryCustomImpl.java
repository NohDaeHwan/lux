package com.used.lux.repository.querydsl;

import com.querydsl.jpa.JPQLQuery;
import com.used.lux.domain.appraisal.Appraisal;
import com.used.lux.domain.appraisal.AppraisalResult;
import com.used.lux.domain.appraisal.QAppraisal;
import com.used.lux.domain.constant.AppraisalState;
import com.used.lux.domain.constant.GenterType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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
                .where(appraisal.appBrand.brandName.like("%"+appraisalBrand+"%"),
                        appraisal.appSize.like("%"+appraisalSize+"%"),
                        appraisal.appProdNm.like("%"+query+"%"),
                        appraisal.createdAt.after(LocalDateTime.of(Integer.parseInt(dateResult[0]),
                                Integer.parseInt(dateResult[1]), Integer.parseInt(dateResult[2]), 00, 00)));

        if (!Objects.equals(appraisalState, "")) {
            queryResult.where(appraisal.appState.eq(AppraisalState.valueOf(appraisalState)));
        }

        if (!Objects.equals(appraisalGender, "")) {
            queryResult.where(appraisal.appGender.eq(GenterType.valueOf(appraisalGender)));
        }

        long totalCount = queryResult.fetchCount();
        List<Appraisal> results = getQuerydsl().applyPagination(pageable, queryResult).fetch();
        return new PageImpl<Appraisal>(results, pageable, totalCount);
    }

}
