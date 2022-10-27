package com.used.lux.request;

public record ProductUpdateRequest(
        String productName,
        String content,
        String brandName,
        String categoryBName,
        String categoryMName,
        String productSellType,
        String stateName,
        int price
) {
    public static ProductUpdateRequest of(String productName, String content, String brandName, String categoryBName,
                                          String categoryMName, String productSellType, String stateName, int price) {
        return new ProductUpdateRequest(productName, content, brandName, categoryBName, categoryMName,
                productSellType, stateName, price);
    }
}
