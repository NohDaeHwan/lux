package com.used.lux.response.appraisal;

import com.used.lux.response.useraccount.UserAccountResponse;

import java.time.LocalDateTime;
import java.util.List;

public record AppraisalRequestResponse(
        Long id,
        String appraisalProductName,
        String appraisalBrandName,
        String appraisalGender,
        String appraisalColor,
        String appraisalSize,
        String appraisalStateName,
        String appraisalStateStep,
        UserAccountResponse userAccount,
        List<AppraisalImageResponse> imageList,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
)  {
}