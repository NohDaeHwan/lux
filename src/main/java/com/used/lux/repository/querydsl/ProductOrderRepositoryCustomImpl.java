package com.used.lux.repository.querydsl;

import com.querydsl.jpa.JPQLQuery;
import com.used.lux.domain.Appraisal;
import com.used.lux.domain.ProductOrder;
import com.used.lux.domain.QProductOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDateTime;
import java.util.List;

public class ProductOrderRepositoryCustomImpl extends QuerydslRepositorySupport implements ProductOrderRepositoryCustom {

    public ProductOrderRepositoryCustomImpl() {
        super(Appraisal.class);
    }

    @Override
    public Page<ProductOrder> searchProductOrder(String orderState, String orderSellType,
                                                 String orderDate, String query, Pageable pageable) {
        QProductOrder productOrder = QProductOrder.productOrder;

        String[] dateResult = orderDate.split("-");

        JPQLQuery<ProductOrder> queryResult = from(productOrder)
                .select(productOrder)
                .where(productOrder.state.stateStep.like("%"+orderState+"%"),
                        productOrder.product.productSellType.like("%"+orderSellType+"%"),
                        productOrder.product.appraisalRequest.appraisalProductName.like("%"+query+"%"),
                        productOrder.modifiedAt.after(LocalDateTime.of(Integer.parseInt(dateResult[0]),
                                Integer.parseInt(dateResult[1]), Integer.parseInt(dateResult[2]), 00, 00)));
        long totalCount = queryResult.fetchCount();
        List<ProductOrder> results = getQuerydsl().applyPagination(pageable, queryResult).fetch();
        return new PageImpl<ProductOrder>(results, pageable, totalCount);
    }

}
