package com.used.lux.dto.user.appraisal;

import com.used.lux.domain.appraisal.AppraisalImage;
import com.used.lux.domain.appraisal.AppraisalRequest;

import java.time.LocalDateTime;

public record AppraisalImageDto(
        Long AppraisalRequestId,
        String origFileName,
        String filePath,
        Long fileSize,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static AppraisalImageDto of(Long AppraisalRequestId, String origFileName, String filePath, Long fileSize) {
        return new AppraisalImageDto(AppraisalRequestId, origFileName, filePath, fileSize, null, null, null, null);
    }

    public static AppraisalImageDto from(AppraisalImage entity) {
        return new AppraisalImageDto(
                entity.getAppraisalRequest().getId(),
                entity.getOrigFileName(),
                entity.getFilePath(),
                entity.getFileSize(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public AppraisalImage toEntity(AppraisalRequest appraisalRequest) {
        return AppraisalImage.of(appraisalRequest, origFileName, filePath, fileSize);
    }

}
