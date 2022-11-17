package com.used.lux.repository.querydsl;

import com.used.lux.domain.order.ProductOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductOrderRepositoryCustom {

    Page<ProductOrder> searchProductOrder(String orderState, String orderSellType, String orderDate,
                                          String query, Pageable pageable);

}
