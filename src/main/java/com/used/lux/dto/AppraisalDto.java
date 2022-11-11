package com.used.lux.dto;

import com.used.lux.domain.Appraisal;
import com.used.lux.domain.UserAccount;

import java.time.LocalDateTime;

public record AppraisalDto(
        Long id,
        String appraisalGrade,
        String appraisalComment,
        int appraisalPrice,
        AppraisalRequestDto appraisalRequest,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
)  {

    public static AppraisalDto of(Long id, String appraisalGrade, String appraisalComment, int appraisalPrice,
                                  AppraisalRequestDto appraisalRequest, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new AppraisalDto(id, appraisalGrade, appraisalComment, appraisalPrice, appraisalRequest, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static AppraisalDto from(Appraisal entity) {
        return new AppraisalDto(
                entity.getId(),
                entity.getAppraisalGrade(),
                entity.getAppraisalComment(),
                entity.getAppraisalPrice(),
                AppraisalRequestDto.from(entity.getAppraisalRequest()),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public Appraisal toEntity(UserAccount userAccount) {
        return Appraisal.of(
                appraisalGrade,
                appraisalComment,
                appraisalPrice,
                appraisalRequest.toEntity(userAccount)
        );
    }

}
