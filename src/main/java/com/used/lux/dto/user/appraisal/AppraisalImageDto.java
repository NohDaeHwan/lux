package com.used.lux.dto.user.appraisal;

import java.time.LocalDateTime;

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
