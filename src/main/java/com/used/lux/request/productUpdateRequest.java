package com.used.lux.request;

public record ProductUpdateRequest(

        String productName,
        String content,
        String brandName,
        String categoryBName,
        String categoryMName,
        String productSellType

) {

    public static ProductUpdateRequest of(String productName, String content, String brandName, String categoryBName, String categoryMName,
                                          String productSellType) {
        return new ProductUpdateRequest(productName, content, brandName, categoryBName, categoryMName, productSellType);
    }



}
