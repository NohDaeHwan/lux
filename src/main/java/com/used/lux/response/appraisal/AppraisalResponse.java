package com.used.lux.response.appraisal;

import com.used.lux.dto.AppraisalDto;

import java.time.LocalDateTime;

public record AppraisalResponse(
        Long id,
        String appraisalGrade,
        String appraisalComment,
        int appraisalPrice,
        AppraisalRequestResponse appraisalRequest,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
)  {

    public static AppraisalResponse of(Long id, String appraisalGrade, String appraisalComment, int appraisalPrice, LocalDateTime createdAt,
                                       AppraisalRequestResponse appraisalRequest, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new AppraisalResponse(id, appraisalGrade, appraisalComment, appraisalPrice, appraisalRequest, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static AppraisalResponse from(AppraisalDto dto) {
        return new AppraisalResponse(
                dto.id(),
                dto.appraisalGrade(),
                dto.appraisalComment(),
                dto.appraisalPrice(),
                AppraisalRequestResponse.from(dto.appraisalRequest()),
                dto.createdAt(),
                dto.createdBy(),
                dto.modifiedAt(),
                dto.modifiedBy()
        );
    }

}