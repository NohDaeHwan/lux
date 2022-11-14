package com.used.lux.request;

public record ProductUpdateRequest(
        String productName,
        String content,
        Long brandId,
        Long categoryBId,
        Long categoryMId,
        String productSellType,
        String stateStep,
        int productPrice
) {
    public static ProductUpdateRequest of(String productName, String content, Long brandId,
                                          Long categoryBId, Long categoryMId,String productSellType, String stateStep, int productPrice) {
        return new ProductUpdateRequest(productName, content, brandId, categoryBId, categoryMId,
                productSellType, stateStep, productPrice);
    }
}
