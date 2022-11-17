package com.used.lux.response.appraisal;

import com.used.lux.dto.user.appraisal.AppraisalImageDto;

import java.time.LocalDateTime;

public record AppraisalImageResponse(
        Long AppraisalRequestId,
        String origFileName,
        String filePath,
        Long fileSize,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
)  {

    public static AppraisalImageResponse of(Long AppraisalRequestId, String origFileName, String filePath, Long fileSize,
                                            LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new AppraisalImageResponse(AppraisalRequestId, origFileName, filePath, fileSize, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static AppraisalImageResponse from(AppraisalImageDto dto) {
        return new AppraisalImageResponse(
                dto.AppraisalRequestId(),
                dto.origFileName(),
                dto.filePath(),
                dto.fileSize(),
                dto.createdAt(),
                dto.createdBy(),
                dto.modifiedAt(),
                dto.modifiedBy()
        );
    }

}