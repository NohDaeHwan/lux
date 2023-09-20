package com.used.lux.response.appraisal;

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
}