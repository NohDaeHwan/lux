package com.used.lux.dto;

import com.used.lux.domain.Appraisal;

import java.time.LocalDateTime;

public record AppraisalDto(
        Long id,
        String appraisalGrade,
        String appraisalComment,
        int appraisalPrice,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
)  {

    public static AppraisalDto of(Long id, String appraisalGrade, String appraisalComment, int appraisalPrice,
                                  LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new AppraisalDto(id, appraisalGrade, appraisalComment, appraisalPrice, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static AppraisalDto from(Appraisal entity) {
        return new AppraisalDto(
                entity.getId(),
                entity.getAppraisalGrade(),
                entity.getAppraisalComment(),
                entity.getAppraisalPrice(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public Appraisal toEntity() {
        return Appraisal.of(
                appraisalGrade,
                appraisalComment,
                appraisalPrice
        );
    }

}
