package com.used.lux.dto.user.appraisal;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AppraisalImageDto(
        Long AppraisalId,
        String origFileName,
        String filePath,
        Long fileSize,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
}
