package com.used.lux.response;

import com.used.lux.domain.State;
import com.used.lux.dto.ProductOrderDto;

import java.time.LocalDateTime;

public record ProductOrderResponse(
        Long id,
        String orderName,
        String orderPhoneNumber,
        String orderAddress,
        String orderEmail,
        String requestedTerm,
        String productName,
        State productState,
        int productPrice,
        String productSellType,
        String userEmail,
        String userName,
        LocalDateTime createdAt,
        String createdBy
) {

    public static ProductOrderResponse of(Long id, String orderName, String orderPhoneNumber, String orderAddress,
                                String orderEmail, String requestedTerm, String productName, State productState,
                                int productPrice, String productSellType, String userEmail, String userName,
                                LocalDateTime createdAt, String createdBy) {
        return new ProductOrderResponse(id, orderName, orderPhoneNumber, orderAddress, orderEmail, requestedTerm,
                productName, productState, productPrice, productSellType, userEmail, userName, createdAt, createdBy);
    }

    public  static ProductOrderResponse from(ProductOrderDto dto){
        return new ProductOrderResponse (
                dto.id(),
                dto.name(),
                dto.phoneNumber(),
                dto.address(),
                dto.email(),
                dto.requestedTerm(),
                dto.productDto().productName(),
                dto.productDto().productState(),
                dto.productDto().productPrice(),
                dto.productDto().productSellType(),
                dto.userAccountDto().userEmail(),
                dto.userAccountDto().userName(),
                dto.createdAt(),
                dto.createdBy()
        );
    }

}
