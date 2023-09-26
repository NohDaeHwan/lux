package com.used.lux.service.user.order;

import com.used.lux.domain.constant.OrderState;
import com.used.lux.domain.constant.ProductState;
import com.used.lux.domain.order.ProductOrder;
import com.used.lux.domain.product.Product;
import com.used.lux.domain.useraccount.UserAccount;
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
    public void createOrder(Principal principal, Long productId, OrderCreateRequest request) {
        Product product = productRepository.findById(productId).get();
        UserAccount userAccount = userAccountRepository.findById(principal.id()).get();
        Long payment = userAccount.getPoint()-product.getProdPrice();

//        State stateOrder = stateRepository.findByStateStep("주문완료");
//        State stateProduct = stateRepository.findByStateStep("판매완료");

        product.setProdState(ProductState.COMPLETE);
        userAccount.setPoint(payment);
        userAccountRepository.save(userAccount);
        productRepository.save(product);
        productOrderRepository.save(ProductOrder.builder()
                        .name(request.name())
                        .phoneNumber(request.phoneNumber())
                        .address(request.address())
                        .email(request.email())
                        .payment(request.payment())
                        .requestedTerm(request.requestTerm())
                        .orderState(OrderState.PAYMENT)
                        .productId(product.getId())
                        .userAccount(userAccount)
                        .build()
        );
//        productOrderLogRepository.save(ProductOrderLog.of(
//                null,
//                principal.userEmail(),
//                productId,
//                product.getAppraisalResult().getAppraisal().getAppraisalProductName(),
//                stateOrder,
//                product.getProductPrice(),
//                product.getProductSellType(),
//                principal.id()
//        ));
//        userAccountLogRepository.save(UserAccountLog.of(
//                null,
//                principal.userEmail(),
//                principal.userGrade(),
//                product.getProductPrice(),
//                "차감",
//                "중고/"+productId
//        ));
    }
}
