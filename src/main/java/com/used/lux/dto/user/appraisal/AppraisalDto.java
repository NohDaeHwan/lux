package com.used.lux.dto.user.appraisal;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record AppraisalDto(
        Long id,
        String appProdNm,
        Long appBrandId,
        String appBrand,
        String appGender,
        String appColor,
        String appSize,
        String appState,
        Long userId,
        String userEmail,
        String userNm,
        Long appResultId,
        Long appPrice,
        String appGrade,
        String appComment,
        List<AppraisalImageDto> imageList,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
)  {
}
