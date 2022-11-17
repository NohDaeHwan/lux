package com.used.lux.service.user.order;

import com.used.lux.domain.*;
import com.used.lux.domain.order.ProductOrder;
import com.used.lux.domain.order.ProductOrderCancel;
import com.used.lux.domain.order.ProductOrderLog;
import com.used.lux.dto.security.Principal;
import com.used.lux.repository.order.ProductOrderCancelRepository;
import com.used.lux.repository.order.ProductOrderLogRepository;
import com.used.lux.repository.order.ProductOrderRepository;
import com.used.lux.repository.StateRepository;
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
    private final StateRepository stateRepository;

    public void orderCancel(Principal principal, Long orderId, OrderCancelRequest orderCancelRequest) {
        ProductOrder order = productOrderRepository.getReferenceById(orderId);
        State state = stateRepository.findByStateStep("취소요청");
        order.setState(state);

        productOrderLogRepository.save(ProductOrderLog.of(
                principal.userEmail(),
                order.getProduct().getId(),
                order.getProduct().getAppraisal().getAppraisalRequest().getAppraisalProductName(),
                state,
                order.getProduct().getProductPrice(),
                order.getProduct().getProductSellType(),
                principal.id()
        ));

        productOrderCancelRepository.save(ProductOrderCancel.of(
                orderId,
                principal.userName(),
                order.getProduct().getAppraisal().getAppraisalRequest().getAppraisalProductName(),
                order.getProduct().getProductPrice(),
                "주문취소할래용!")
        );

    }

    public Long count() {
        return productOrderCancelRepository.count();
    }

}