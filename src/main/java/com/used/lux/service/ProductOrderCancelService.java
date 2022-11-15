package com.used.lux.service;

import com.used.lux.domain.*;
import com.used.lux.dto.security.Principal;
import com.used.lux.repository.ProductOrderCancelRepository;
import com.used.lux.repository.ProductOrderLogRepository;
import com.used.lux.repository.ProductOrderRepository;
import com.used.lux.repository.StateRepository;
import com.used.lux.request.OrderCancelRequest;
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
                order.getProduct().getProductSellType()
        ));

        productOrderCancelRepository.save(ProductOrderCancel.of(
                orderId,
                principal.userName(),
                order.getProduct().getAppraisal().getAppraisalRequest().getAppraisalProductName(),
                order.getProduct().getProductPrice(),
                "주문취소할래용!")
        );

    }
}