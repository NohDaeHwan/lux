package com.used.lux.dto;

import com.used.lux.domain.Appraisal;
import com.used.lux.domain.Brand;
import com.used.lux.domain.State;
import com.used.lux.domain.UserAccount;

import java.time.LocalDateTime;

public record AppraisalDto(
        Long id,
        String appraisalName,
        Brand appraisalBrand,
        String appraisalContent,
        String appraisalGender,
        String appraisalColor,
        String appraisalSize,
        String appraisalGrade,
        State appraisalState,
        String appraisalComment,
        int appraisalPrice,
        UserAccountDto userAccountDto,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
)  {

    public static AppraisalDto of(Long id, String appraisalName, Brand appraisalBrand, String appraisalContent,
                                  String appraisalGender, String appraisalColor, String appraisalSize, String appraisalGrade, State appraisalState,
                                  String appraisalComment, int appraisalPrice, UserAccountDto userAccountDto, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new AppraisalDto(id, appraisalName, appraisalBrand, appraisalContent, appraisalGender, appraisalColor,
                appraisalSize, appraisalGrade, appraisalState, appraisalComment, appraisalPrice, userAccountDto,
                createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static AppraisalDto of(String appraisalName, Brand appraisalBrand, String appraisalContent,
                                  String appraisalGender, String appraisalColor, String appraisalSize, UserAccountDto userAccountDto) {
        return new AppraisalDto(null, appraisalName, appraisalBrand, appraisalContent, appraisalGender, appraisalColor,
                appraisalSize, null, null, null, 0, userAccountDto,
                null, null, null, null);
    }

    public static AppraisalDto from(Appraisal entity) {
        return new AppraisalDto(
                entity.getId(),
                entity.getAppraisalProductName(),
                entity.getAppraisalBrand(),
                entity.getAppraisalContent(),
                entity.getAppraisalGender(),
                entity.getAppraisalColor(),
                entity.getAppraisalSize(),
                entity.getAppraisalGrade(),
                entity.getAppraisalState(),
                entity.getAppraisalComment(),
                entity.getAppraisalPrice(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public Appraisal toEntity(UserAccount userAccount) {
        return Appraisal.of(
                appraisalName,
                appraisalBrand,
                appraisalContent,
                appraisalGender,
                appraisalColor,
                appraisalSize,
                appraisalGrade,
                appraisalState,
                appraisalComment,
                appraisalPrice,
                userAccount
        );
    }

}
