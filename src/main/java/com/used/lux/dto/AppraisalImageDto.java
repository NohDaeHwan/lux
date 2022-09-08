package com.used.lux.dto;

import com.used.lux.domain.AppraisalImage;
import com.used.lux.domain.ForAppraisal;
import com.used.lux.domain.UserAccount;

import java.time.LocalDateTime;

public record AppraisalImageDto(
        Long appraisalId,
        String origFileName,
        String filePath,
        Long fileSize,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static AppraisalImageDto of(Long appraisalId, String origFileName, String filePath, Long fileSize) {
        return new AppraisalImageDto(appraisalId, origFileName, filePath, fileSize, null, null, null, null);
    }

    public static AppraisalImageDto from(AppraisalImage entity) {
        return new AppraisalImageDto(
                entity.getForAppraisal().getId(),
                entity.getOrigFileName(),
                entity.getFilePath(),
                entity.getFileSize(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public AppraisalImage toEntity(ForAppraisal forAppraisal, UserAccount userAccount) {
        return AppraisalImage.of(forAppraisal, userAccount, origFileName, filePath, fileSize);
    }

}
