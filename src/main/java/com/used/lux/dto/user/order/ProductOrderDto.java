package com.used.lux.dto.user.order;

import com.used.lux.dto.user.product.ProductDto;
import com.used.lux.dto.user.useraccount.UserAccountDto;

import java.time.LocalDateTime;

public record ProductOrderDto(
        Long id,
        String name, // 주문자명
        String phoneNumber, // 주문자 핸드폰번호
        String address, // 주문자 주소
        String email, // 주문자 이메일
        int payment, // 결제가격
        String requestedTerm, // 주문자 요청사항
        String orderState,
        ProductDto productDto, // 주문한 상품 정보
        UserAccountDto userAccountDto, // 주문한 유저 정보
        LocalDateTime createdAt, // 주문 날짜
        String createdBy,
        LocalDateTime modifiedAt, // 변경 날짜
        String modifiedBy
) {
}
