package com.used.lux.request.product;

public record ProductUpdateRequest(
        String productName,
        String content,
        Long brandId,
        Long categoryBId,
        Long categoryMId,
        String productSellType,
        String stateStep,
        Long productPrice
) {
    public static ProductUpdateRequest of(String productName, String content, Long brandId,
                                          Long categoryBId, Long categoryMId,String productSellType, String stateStep, Long productPrice) {
        return new ProductUpdateRequest(productName, content, brandId, categoryBId, categoryMId,
                productSellType, stateStep, productPrice);
    }
}
