package com.used.lux.dto.user.appraisal;

import com.used.lux.dto.BrandDto;
import com.used.lux.dto.user.useraccount.UserAccountDto;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record AppraisalDto(
        Long id,
        String appProdNm,
        String appBrand,
        String appGender,
        String appColor,
        String appSize,
        String appState,
        Long userId,
        String userEmail,
        String userNm,
        Long appResultId,
        List<AppraisalImageDto> imageList,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
)  {
}
