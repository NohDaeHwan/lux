package com.used.lux.dto.user.appraisal;

import com.used.lux.domain.appraisal.AppraisalRequest;
import com.used.lux.domain.useraccount.UserAccount;
import com.used.lux.dto.BrandDto;
import com.used.lux.dto.StateDto;
import com.used.lux.dto.user.useraccount.UserAccountDto;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record AppraisalRequestDto(
        Long id,
        String appraisalProductName,
        BrandDto appraisalBrand,
        String appraisalGender,
        String appraisalColor,
        String appraisalSize,
        StateDto appraisalState,
        UserAccountDto userAccount,
        Set<AppraisalImageDto> imageList,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
)  {
    public static AppraisalRequestDto of(Long id, String appraisalProductName, BrandDto appraisalBrand, String appraisalGender,
                               String appraisalColor, String appraisalSize, StateDto appraisalState, UserAccountDto userAccount,
                                         Set<AppraisalImageDto> imageList, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new AppraisalRequestDto(id, appraisalProductName, appraisalBrand, appraisalGender, appraisalColor, appraisalSize,
                appraisalState, userAccount, imageList, createdAt, createdBy, modifiedAt, modifiedBy);
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
                entity.getImages().stream()
                        .map(AppraisalImageDto::from)
                        .collect(Collectors.toUnmodifiableSet()),
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
