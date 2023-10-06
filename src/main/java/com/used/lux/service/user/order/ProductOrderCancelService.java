package com.used.lux.service.user.order;

import com.used.lux.domain.constant.OrderState;
import com.used.lux.domain.order.ProductOrder;
import com.used.lux.domain.order.ProductOrderCancel;
import com.used.lux.dto.security.Principal;
import com.used.lux.repository.order.ProductOrderCancelRepository;
import com.used.lux.repository.order.ProductOrderLogRepository;
import com.used.lux.repository.order.ProductOrderRepository;
import com.used.lux.request.order.OrderCancelRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductOrderCancelService {
    //ProductOrderCancle DB의 서비스 클래스입니다. 해당 DB에 연결하려면 repository와 service 영역에 추가적인 작성이 필요합니다.
    private final ProductOrderRepository productOrderRepository;
    private final ProductOrderCancelRepository productOrderCancelRepository;
    private final ProductOrderLogRepository productOrderLogRepository;

    public void orderCancel(Principal principal, Long orderId, OrderCancelRequest orderCancelRequest) {
        ProductOrder order = productOrderRepository.getReferenceById(orderId);
        order.setOrderState(OrderState.CANCELED_REQUEST);

//        productOrderLogRepository.save(ProductOrderLog.of(
//                principal.userEmail(),
//                order.getProductId(),
//                order.getAppraisalProductName(),
//                state,
//                order.getProductPrice(),
//                order.getProduct().getProductSellType(),
//                principal.id()
//        ));

        productOrderCancelRepository.save(ProductOrderCancel.builder()
                        .id(null)
                        .userId(principal.id())
                        .orderId(orderId)
                        .cancelTerm("주문취소할래용!")
                        .build()
        );

    }

    public Long count() {
        return productOrderCancelRepository.count();
    }

}