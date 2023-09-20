package com.used.lux.response.order;

import com.used.lux.domain.State;
import com.used.lux.dto.user.order.ProductOrderDto;
import com.used.lux.dto.user.product.ImageDto;

import java.time.LocalDateTime;
import java.util.List;

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
        List<ImageDto> imageDtoList,
        String userEmail,
        String userName,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
}
