package com.used.lux.dto.user.product;

import com.used.lux.domain.*;

import java.time.LocalDateTime;
import java.util.List;

public record ProductDto(
        Long id,
        Long appraisalId,
        String productName,
        Long productBrandId,
        String productBrandName,
        String productGender,
        String productColor,
        String productSize,
        int appraisalPrice,
        String appraisalComment,
        String productGrade,
        Long categoryBId,
        String categoryBName,
        Long categoryMId,
        String categoryMName,
        State productState,
        int productPrice,
        String productSellType,
        String productContent,
        int productViewCount,
        List<ImageDto> imageDtos,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
}
