package com.used.lux.dto.user.appraisal;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AppraisalResultDto(
        Long id,
        String appGrade,
        String appComment,
        Long appPrice,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
)  {
}
