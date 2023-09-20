package com.used.lux.request.product;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record ProductCreateRequest(
        Long productId,
        String productName,
        Long productPrice,
        Long brandId,
        Long categoryBId,
        Long categoryMId,
        List<MultipartFile> images,
        String productContent
) {
    public static ProductCreateRequest of(Long productId, String productName, Long productPrice,
                                          Long brandId, Long categoryBId,
                                          Long categoryMId, List<MultipartFile> images, String productContent) {
        return new ProductCreateRequest(productId, productName, productPrice, brandId,
                categoryBId, categoryMId, images, productContent);
    }
}