package com.used.lux.dto.user.appraisal;

import java.time.LocalDateTime;

public record AppraisalRequestLogDto(
        Long id,
        String appProdNm,
        String appGrade,
        long appPrice,
        String appState,
        Long userId,
        Long appId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
}
