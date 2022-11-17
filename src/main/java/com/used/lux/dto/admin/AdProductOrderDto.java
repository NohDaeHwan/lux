package com.used.lux.dto.admin;

import com.used.lux.dto.user.order.ProductOrderCancelDto;
import com.used.lux.dto.user.order.ProductOrderDto;

public record AdProductOrderDto(
        ProductOrderDto productOrderDto,
        ProductOrderCancelDto productOrderCancelDto
) {
    public static AdProductOrderDto of(ProductOrderDto productOrderDto, ProductOrderCancelDto productOrderCancelDto) {
        return new AdProductOrderDto(productOrderDto, productOrderCancelDto);
    }
}
