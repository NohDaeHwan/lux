package com.used.lux.response.appraisal;

import com.used.lux.domain.AppraisalImage;
import com.used.lux.dto.ForAppraisalsDto;

import java.time.LocalDateTime;
import java.util.Set;

public record ForAppraisalResponse(
        String productName,
        String brandName,
        String content,
        String lived,
        String purchase,
        int purchasePrice,
        boolean whetherApprisal,
        String userEmail,
        String nickName,
        Set<AppraisalImage> appraisalImages,
        LocalDateTime createdAt,
        String createdBy
)  {

    public static ForAppraisalResponse of(String productName, String brandName, String content, String lived, String purchase,
                                          int purchasePrice, boolean whetherApprisal, String userEmail, String nickName,
                                          Set<AppraisalImage> appraisalImages, LocalDateTime createdAt, String createdBy) {
        return new ForAppraisalResponse(productName, brandName, content, lived, purchase, purchasePrice,
                whetherApprisal, userEmail, nickName, appraisalImages, createdAt, createdBy);
    }

    public static ForAppraisalResponse from(ForAppraisalsDto dto) {
        return new ForAppraisalResponse(
                dto.productName(),
                dto.brandName(),
                dto.content(),
                dto.lived(),
                dto.purchase(),
                dto.purchasePrice(),
                dto.whetherApprisal(),
                dto.userAccountDto().userEmail(),
                dto.userAccountDto().nickName(),
                dto.appraisalImages(),
                dto.createdAt(),
                dto.createdBy()
        );
    }

}