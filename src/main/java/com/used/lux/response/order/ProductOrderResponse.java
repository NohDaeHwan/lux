package com.used.lux.response.order;

import com.used.lux.domain.State;
import com.used.lux.dto.user.order.ProductOrderDto;

import java.time.LocalDateTime;

public record ProductOrderResponse(
        Long id,
        String orderName,
        String orderPhoneNumber,
        String orderAddress,
        String orderEmail,
        int payment,
        String requestedTerm,
        String stateName,
        String stateStep,
        String productName,
        String productBrandName,
        State productState,
        int productPrice,
        String productSellType,
        String userEmail,
        String userName,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static ProductOrderResponse of(Long id, String orderName, String orderPhoneNumber, String orderAddress,
                                String orderEmail, int payment, String requestedTerm, String stateName, String stateStep,
                                          String productName,String productBrandName, State productState,
                                int productPrice, String productSellType, String userEmail, String userName,
                                LocalDateTime createdAt, String createdBy,LocalDateTime modifiedAt, String modifiedBy) {
        return new ProductOrderResponse(id, orderName, orderPhoneNumber, orderAddress, orderEmail, payment, requestedTerm,
                stateName, stateStep, productName,productBrandName, productState, productPrice, productSellType, userEmail,
                userName, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public  static ProductOrderResponse from(ProductOrderDto dto){
        return new ProductOrderResponse (
                dto.id(),
                dto.name(),
                dto.phoneNumber(),
                dto.address(),
                dto.email(),
                dto.payment(),
                dto.requestedTerm(),
                dto.stateDto().stateName(),
                dto.stateDto().stateStep(),
                dto.productDto().productName(),
                dto.productDto().productBrandName(),
                dto.productDto().productState(),
                dto.productDto().productPrice(),
                dto.productDto().productSellType(),
                dto.userAccountDto().userEmail(),
                dto.userAccountDto().userName(),
                dto.createdAt(),
                dto.createdBy(),
                dto.modifiedAt(),
                dto.modifiedBy()
        );
    }

}
