package com.used.lux.request.product;

public record ProductUpdateRequest(
        String prodNm,
        String prodContent,
        Long brandId,
        Long cateBId,
        Long cateMId,
        String prodSellType,
        String prodState,
        Long prodPrice
) {
}
