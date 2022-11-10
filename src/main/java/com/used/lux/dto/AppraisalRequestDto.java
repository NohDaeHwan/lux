package com.used.lux.dto;

import com.used.lux.domain.*;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public record AppraisalRequestDto(
        Long id,
        String appraisalProductName,
        BrandDto appraisalBrand,
        String appraisalGender,
        String appraisalColor,
        String appraisalSize,
        StateDto appraisalState,
        UserAccountDto userAccount,
        Long appraisalId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
)  {
    public static AppraisalRequestDto of(Long id, String appraisalProductName, BrandDto appraisalBrand, String appraisalGender,
                               String appraisalColor, String appraisalSize, StateDto appraisalState, UserAccountDto userAccount,
                               Long appraisalId, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new AppraisalRequestDto(id, appraisalProductName, appraisalBrand, appraisalGender, appraisalColor, appraisalSize,
                appraisalState, userAccount, appraisalId, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static AppraisalRequestDto of(String appraisalProductName, BrandDto appraisalBrand, String appraisalGender,
                                         String appraisalColor, String appraisalSize, StateDto appraisalState, UserAccountDto userAccount) {
        return new AppraisalRequestDto(null, appraisalProductName, appraisalBrand, appraisalGender, appraisalColor, appraisalSize,
                appraisalState, userAccount, null, null, null, null, null);
    }

    public static AppraisalRequestDto from(AppraisalRequest entity) {
        return new AppraisalRequestDto(
                entity.getId(),
                entity.getAppraisalProductName(),
                BrandDto.from(entity.getAppraisalBrand()),
                entity.getAppraisalGender(),
                entity.getAppraisalColor(),
                entity.getAppraisalSize(),
                StateDto.from(entity.getAppraisalState()),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getAppraisalId(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public AppraisalRequest toEntity(UserAccount userAccount) {
        return AppraisalRequest.of(
                appraisalProductName,
                appraisalBrand.toDto(),
                appraisalGender,
                appraisalColor,
                appraisalSize,
                appraisalState.toDto(),
                userAccount
        );
    }

}
