package com.used.lux.service.admin;

import com.used.lux.domain.constant.OrderState;
import com.used.lux.domain.constant.ProductState;
import com.used.lux.domain.order.ProductOrder;
import com.used.lux.domain.order.ProductOrderCancel;
import com.used.lux.domain.product.Product;
import com.used.lux.domain.useraccount.UserAccount;
import com.used.lux.dto.user.order.ProductOrderCancelDto;
import com.used.lux.dto.user.order.ProductOrderDto;
import com.used.lux.mapper.ProductOrderCancelMapper;
import com.used.lux.mapper.ProductOrderMapper;
import com.used.lux.repository.order.ProductOrderCancelRepository;
import com.used.lux.repository.order.ProductOrderLogRepository;
import com.used.lux.repository.order.ProductOrderRepository;
import com.used.lux.repository.product.ProductLogRepository;
import com.used.lux.repository.product.ProductRepository;
import com.used.lux.repository.useraccount.UserAccountLogRepository;
import com.used.lux.repository.useraccount.UserAccountRepository;
import com.used.lux.request.OrderUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AdOrderService {

    private final ProductOrderRepository productOrderRepository;
    private final ProductOrderMapper productOrderMapper;
    private final ProductOrderLogRepository productOrderLogRepository;

    private final ProductOrderCancelRepository productOrderCancelRepository;
    private final ProductOrderCancelMapper prodOrderCancelMapper;

    private final ProductRepository productRepository;
    private final ProductLogRepository productLogRepository;
    private final UserAccountRepository userAccountRepository;
    private final UserAccountLogRepository userAccountLogRepository;


    public Page<ProductOrderDto> getOrderList(String orderState, String orderSellType, String orderDate, String query,
                                              Pageable pageable) {
        String[] dateResult = orderDate.split("-");
        LocalDateTime date = LocalDateTime.of(Integer.parseInt(dateResult[0]),
                Integer.parseInt(dateResult[1]), Integer.parseInt(dateResult[2]), 00, 00);
        return productOrderRepository.findByBackProductOrderList(orderState, orderSellType, date,
                query, pageable).map(productOrderMapper::toDto);
    }

    public ProductOrderDto getOrderDetail(Long orderId) {
        ProductOrderDto productOrderDto = productOrderMapper.toDto(productOrderRepository.findById(orderId).get());
        return productOrderDto;
    }

    public ProductOrderCancelDto getOrderCancel(Long orderId) {
        ProductOrderCancel cancel = productOrderCancelRepository.findByOrderId(orderId);
        return prodOrderCancelMapper.toDtoCustom(
                cancel,
                userAccountRepository.findById(cancel.getUserId()).get(),
                productRepository.findById(cancel.getOrderId()).get()
        );
    }

    public void updateCancel(Long orderId, OrderUpdateRequest orderUpdateRequest) {
//        State stateCancel = stateRepository.findByStateStep("주문취소");
//        State stateWait = stateRepository.findByStateStep("판매대기");

        Product product = productRepository.getReferenceById(orderUpdateRequest.productId());
        UserAccount userAccount = userAccountRepository.getReferenceById(orderUpdateRequest.userId());
        ProductOrder productOrder = productOrderRepository.getReferenceById(orderId);

        productOrder.setOrderState(OrderState.CANCELED);
        productOrderRepository.save(productOrder);
//        productOrderLogRepository.save(ProductOrderLog.of(
//                null,
//                userAccount.getUserEmail(),
//                product.getId(),
//                product.getAppraisalResult().getAppraisal().getAppraisalProductName(),
//                stateCancel,
//                product.getProductPrice(),
//                product.getProductSellType(),
//                userAccount.getId()));
//        productLogRepository.save(ProductLog.of(
//                null,
//                product.getId(), product.getAppraisalResult().getAppraisal().getAppraisalProductName(),
//                stateWait,
//                product.getCategoryB(),
//                product.getCategoryM(),
//                product.getProductPrice(),
//                product.getProductSellType()));
        product.setProdState(ProductState.WAITING);
        productRepository.save(product);
        userAccount.setUserPoint(userAccount.getUserPoint() + productOrder.getPayment());
        userAccountRepository.save(userAccount);
//        userAccountLogRepository.save(UserAccountLog.of(null, userAccount.getUserEmail(), userAccount.getUserGrade(),
//                userAccount.getPoint() + product.getProdPrice(), "충전", "주문번호" + orderId + "/상품환불"));
    }
}
