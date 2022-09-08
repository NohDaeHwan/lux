package com.used.lux.dto;

import com.used.lux.domain.AppraisalImage;
import com.used.lux.domain.ForAppraisal;
import com.used.lux.domain.UserAccount;

import java.time.LocalDateTime;
import java.util.Set;

public record ForAppraisalsDto(
        Long id,
        String productName,
        String brandName,
        String content,
        String lived,
        String purchase,
        int purchasePrice,
        boolean whetherApprisal,
        UserAccountDto userAccountDto,
        Set<AppraisalImage> appraisalImages,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
)  {

    public static ForAppraisalsDto of(String productName, String brandName, String content, String lived,
                                      String purchase, int purchasePrice, boolean whetherApprisal,
                                      UserAccountDto userAccountDto, Set<AppraisalImage> appraisalImages) {
        return new ForAppraisalsDto(null, productName, brandName, content, lived, purchase, purchasePrice, whetherApprisal,
                userAccountDto, appraisalImages, null, null, null, null);
    }

    public static ForAppraisalsDto from(ForAppraisal entity) {
        return new ForAppraisalsDto(
                entity.getId(),
                entity.getProductName(),
                entity.getBrandName(),
                entity.getContent(),
                entity.getLived(),
                entity.getPurchase(),
                entity.getPurchasePrice(),
                entity.isWhetherApprisal(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getAppraisalImage(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }



    public ForAppraisal toEntity(UserAccount userAccount) {
        return ForAppraisal.of(
                userAccount,
                productName,
                brandName,
                content,
                lived,
                purchase,
                purchasePrice
        );
    }

}
