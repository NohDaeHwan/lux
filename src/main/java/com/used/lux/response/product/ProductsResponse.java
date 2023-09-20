package com.used.lux.response.product;

import com.used.lux.domain.State;

import java.time.LocalDateTime;

public record ProductsResponse(
        Long id,
        String productName,
        String productBrand,
        String productGrade,
        State productState,
        String productColor,
        int productPrice,
        String productSellType,
        int productViewCount,
        String imagePath,
        LocalDateTime createdAt,
        String createdBy
)  {
}
