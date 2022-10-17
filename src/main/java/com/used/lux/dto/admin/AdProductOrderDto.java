package com.used.lux.dto.admin;

import com.used.lux.dto.*;

public record AdProductOrderDto(
        ProductOrderDto productOrderDto,
        ProductOrderCancelDto productOrderCancelDto
) {
    public static AdProductOrderDto of(ProductOrderDto productOrderDto, ProductOrderCancelDto productOrderCancelDto) {
        return new AdProductOrderDto(productOrderDto, productOrderCancelDto);
    }
}
