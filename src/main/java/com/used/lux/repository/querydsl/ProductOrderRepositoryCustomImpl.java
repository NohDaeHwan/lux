package com.used.lux.repository.querydsl;

import com.querydsl.jpa.JPQLQuery;
import com.used.lux.domain.constant.OrderState;
import com.used.lux.domain.constant.SellType;
import com.used.lux.domain.order.ProductOrder;
import com.used.lux.domain.order.QProductOrder;
import com.used.lux.domain.product.QProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDateTime;
import java.util.List;

public class ProductOrderRepositoryCustomImpl extends QuerydslRepositorySupport implements ProductOrderRepositoryCustom {

    public ProductOrderRepositoryCustomImpl() {
        super(ProductOrder.class);
    }

    @Override
    public Page<ProductOrder> searchProductOrder(String orderState, String orderSellType,
                                                 String orderDate, String query, Pageable pageable) {
        QProductOrder productOrder = QProductOrder.productOrder;
        QProduct product = QProduct.product;

        String[] dateResult = orderDate.split("-");

        JPQLQuery<ProductOrder> queryResult = from(productOrder, product)
                .select(productOrder)
                .where(productOrder.productId.eq(product.id),
                        product.prodNm.like("%"+query+"%"),
                        productOrder.modifiedAt.after(LocalDateTime.of(Integer.parseInt(dateResult[0]),
                                Integer.parseInt(dateResult[1]), Integer.parseInt(dateResult[2]), 00, 00)));

        if (orderState != null) {
            queryResult.where(productOrder.orderState.eq(OrderState.valueOf(orderState)));
        }
        if (orderSellType != null) {
          queryResult.where(productOrder.prodSellType.eq(SellType.valueOf(orderSellType)));
        }

        long totalCount = queryResult.fetchCount();
        List<ProductOrder> results = getQuerydsl().applyPagination(pageable, queryResult).fetch();
        return new PageImpl<ProductOrder>(results, pageable, totalCount);
    }

}
