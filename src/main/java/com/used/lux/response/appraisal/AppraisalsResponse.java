package com.used.lux.response.appraisal;

import com.used.lux.domain.State;
import com.used.lux.dto.AppraisalDto;

import java.time.LocalDateTime;

public record AppraisalsResponse(
        Long id,
        String appraisalName,
        String appraisalBrandName,
        String appraisalGrade,
        State appraisalState,
        String userEmail,
        String userName,
        LocalDateTime createdAt,
        String createdBy
)  {

    public static AppraisalsResponse of(Long id, String appraisalName, String appraisalBrandName, String appraisalGrade,
                              State appraisalState, String userEmail, String userName, LocalDateTime createdAt,
                              String createdBy) {
        return new AppraisalsResponse(id, appraisalName, appraisalBrandName, appraisalGrade, appraisalState,
                userEmail, userName, createdAt, createdBy);
    }

    public static AppraisalsResponse from(AppraisalDto dto) {
        return new AppraisalsResponse(
                dto.id(),
                dto.appraisalName(),
                dto.appraisalBrand().getBrandName(),
                dto.appraisalGrade(),
                dto.appraisalState(),
                dto.userAccountDto().userEmail(),
                dto.userAccountDto().userName(),
                dto.createdAt(),
                dto.createdBy()
        );
    }

}
