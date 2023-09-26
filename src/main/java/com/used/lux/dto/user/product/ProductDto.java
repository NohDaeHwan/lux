package com.used.lux.dto.user.product;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ProductDto(
        Long id,
        String prodNm,
        String prodCateB,
        String prodCateM,
        String prodBrand,
        String prodGender,
        String prodColor,
        String prodSize,
        String prodGrade,
        String prodState,
        Long prodPrice,
        String prodContent,
        int prodViewCnt,
        String prodSellType,
        List<ImageDto> images,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
}
