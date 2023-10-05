package com.used.lux.service.user.order;

import com.used.lux.domain.constant.OrderState;
import com.used.lux.domain.constant.ProductState;
import com.used.lux.domain.constant.SellType;
import com.used.lux.domain.order.ProductOrder;
import com.used.lux.domain.order.ProductOrderLog;
import com.used.lux.domain.product.Product;
import com.used.lux.domain.useraccount.UserAccount;
import com.used.lux.domain.useraccount.UserAccountLog;
import com.used.lux.dto.user.order.ProductOrderDto;
import com.used.lux.dto.security.Principal;
import com.used.lux.mapper.ProductOrderMapper;
import com.used.lux.repository.order.ProductOrderLogRepository;
import com.used.lux.repository.order.ProductOrderRepository;
import com.used.lux.repository.product.ProductRepository;
import com.used.lux.repository.useraccount.UserAccountLogRepository;
import com.used.lux.repository.useraccount.UserAccountRepository;
import com.used.lux.request.order.OrderCreateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ProductOrderService {

    private final ProductOrderRepository productOrderRepository;
    private final ProductOrderMapper productOrderMapper;

    private final ProductRepository productRepository;

    private final ProductOrderLogRepository productOrderLogRepository;
    private final UserAccountRepository userAccountRepository;
    private final UserAccountLogRepository userAccountLogRepository;

    public Page<ProductOrderDto> productListAll(Long id, Pageable pageable){
        return productOrderRepository.findByUserAccountId(id, pageable).map(productOrderMapper::toDto);
    }

    public Page<ProductOrder> orderlistPage(Long id, Pageable pageable){
        Page<ProductOrder> paging = productOrderRepository.findByUserAccountId(id, pageable);
        return paging;
    }

    @Transactional
    public void createOrder(Principal principal, OrderCreateRequest request) {
        Product product = productRepository.getReferenceById(request.prodId());
        UserAccount userAccount = userAccountRepository.getReferenceById(principal.id());
        Long payment = userAccount.getUserPoint()- request.payment();

        product.setProdState(ProductState.COMPLETE);
        userAccount.setUserPoint(payment);
        productOrderRepository.save(ProductOrder.builder()
                        .name(request.name())
                        .phoneNumber(request.phoneNumber())
                        .zoneCode(request.zoneCode())
                        .address(request.addr())
                        .payment(request.payment())
                        .requestedTerm(request.requestTerm())
                        .orderState(OrderState.PAYMENT)
                        .prodSellType(product.getProdSellType())
                        .productId(product.getId())
                        .userAccount(userAccount)
                        .build()
        );
        productOrderLogRepository.save(ProductOrderLog.builder()
                        .id(null)
                        .userId(userAccount.getId())
                        .prodId(product.getId())
                        .prodNm(product.getProdNm())
                        .prodState(ProductState.COMPLETE)
                        .orderPrice(request.payment())
                        .prodSellType(product.getProdSellType())
                        .build());

        userAccountLogRepository.save(UserAccountLog.builder()
                        .id(null)
                        .userId(userAccount.getId())
                        .orderId(product.getId())
                        .userGrade(userAccount.getUserGrade())
                        .point(request.payment())
                        .usageDetail("차감")
                        .saleNumber("중고/"+product.getId())
                .build());
    }
}
