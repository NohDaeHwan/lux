package com.used.lux.dto.user.appraisal;

import com.used.lux.domain.State;
import com.used.lux.domain.appraisal.AppraisalRequestLog;

import java.time.LocalDateTime;

public record AppraisalRequestLogDto(
        Long id,
        String appraisalProductName,
        String appraisalGrade,
        int appraisalPrice,
        State appraisalState,
        Long userId,
        Long appraisalId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static AppraisalRequestLogDto of(Long id, String appraisalProductName, String appraisalGrade, int appraisalPrice, State appraisalState,Long userId,Long appraisalId,
                                            LocalDateTime createdAt,String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new AppraisalRequestLogDto(id, appraisalProductName, appraisalGrade, appraisalPrice,appraisalState, userId,appraisalId, createdAt, createdBy, modifiedAt, modifiedBy);
    }


    public static AppraisalRequestLogDto from(AppraisalRequestLog entity) {
        return new AppraisalRequestLogDto(
                entity.getId(),
                entity.getAppraisalProductName(),
                entity.getAppraisalGrade(),
                entity.getAppraisalPrice(),
                entity.getAppraisalState(),
                entity.getUserId(),
                entity.getAppraisalId(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

}
