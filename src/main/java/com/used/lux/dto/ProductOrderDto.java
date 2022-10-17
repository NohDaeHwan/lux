package com.used.lux.dto;

import com.used.lux.domain.Product;
import com.used.lux.domain.ProductOrder;
import com.used.lux.domain.State;
import com.used.lux.domain.UserAccount;

import java.time.LocalDateTime;

public record ProductOrderDto(
        Long id,
        String name, // 주문자명
        String phoneNumber, // 주문자 핸드폰번호
        String address, // 주문자 주소
        String email, // 주문자 이메일
        String requestedTerm, // 주문자 요청사항

        StateDto stateDto,
        ProductDto productDto, // 주문한 상품 정보
        UserAccountDto userAccountDto, // 주문한 유저 정보
        LocalDateTime createdAt, // 주문 날짜
        String createdBy,
        LocalDateTime modifiedAt, // 변경 날짜
        String modifiedBy
) {

    public static ProductOrderDto of(Long id, String name, String phoneNumber, String address, String email,
                           String requestedTerm, StateDto stateDto, ProductDto productDto, UserAccountDto userAccountDto,
                           LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new ProductOrderDto(id, name, phoneNumber, address, email, requestedTerm, stateDto,
                productDto, userAccountDto, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static ProductOrderDto from(ProductOrder entity){
        return  new ProductOrderDto(
                entity.getId(),
                entity.getName(),
                entity.getPhoneNumber(),
                entity.getAddress(),
                entity.getEmail(),
                entity.getRequestedTerm(),
                StateDto.from(entity.getState()),
                ProductDto.from(entity.getProduct()),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public ProductOrder toEntity(State state, Product product, UserAccount userAccount) {
        return ProductOrder.of(name, phoneNumber, address, email, requestedTerm, state, product, userAccount);
    }

}
