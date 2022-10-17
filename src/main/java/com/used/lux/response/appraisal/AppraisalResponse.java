package com.used.lux.response.appraisal;

import com.used.lux.dto.AppraisalDto;

import java.time.LocalDateTime;

public record AppraisalResponse(
        Long id,
        String appraisalName,
        String appraisalBrandName,
        String appraisalContent,
        String appraisalGender,
        String appraisalColor,
        String appraisalSize,
        String appraisalGrade,
        String appraisalStateName,
        String appraisalStateStep,
        String appraisalComment,
        int appraisalPrice,
        Long userId,
        String userEmail,
        String userName,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
)  {

    public static AppraisalResponse of(Long id, String appraisalName, String appraisalBrandName, String appraisalContent,
                             String appraisalGender, String appraisalColor, String appraisalSize,
                             String appraisalGrade, String appraisalStateName, String appraisalStateStep, String appraisalComment,
                                       Long userId, int appraisalPrice, String userEmail, String userName, LocalDateTime createdAt,
                             String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new AppraisalResponse(id, appraisalName, appraisalBrandName, appraisalContent, appraisalGender,
                appraisalColor, appraisalSize, appraisalGrade, appraisalStateName, appraisalStateStep, appraisalComment, appraisalPrice,
                userId, userEmail, userName, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static AppraisalResponse from(AppraisalDto dto) {
        return new AppraisalResponse(
                dto.id(),
                dto.appraisalName(),
                dto.appraisalBrand().getBrandName(),
                dto.appraisalContent(),
                dto.appraisalGender(),
                dto.appraisalColor(),
                dto.appraisalSize(),
                dto.appraisalGrade(),
                dto.appraisalState().getStateName(),
                dto.appraisalState().getStateStep(),
                dto.appraisalComment(),
                dto.appraisalPrice(),
                dto.userAccountDto().id(),
                dto.userAccountDto().userEmail(),
                dto.userAccountDto().userName(),
                dto.createdAt(),
                dto.createdBy(),
                dto.modifiedAt(),
                dto.modifiedBy()
        );
    }

}