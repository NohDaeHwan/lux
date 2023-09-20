package com.used.lux.response.product;

import com.used.lux.domain.State;
import com.used.lux.dto.user.product.ImageDto;

import java.time.LocalDateTime;
import java.util.List;

public record ProductResponse(
        Long id,
        String productName,
        String productBrandName,
        String productGender,
        String productColor,
        String productSize,
        String productGrade,
        String categoryBName,
        String categoryMName,
        State productState,
        int productPrice,
        List<ImageDto> imageList,
        String productSellType,
        String productContent,
        int productViewCount,
        LocalDateTime createdAt,
        String createdBy
) {
}
