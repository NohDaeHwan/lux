package com.used.lux.response.appraisal;

import com.used.lux.domain.AppraisalImage;
import com.used.lux.dto.ForAppraisalsDto;

import java.time.LocalDateTime;
import java.util.Set;

public record ForAppraisalsResponse(
        String productName,
        String content,
        String userEmail,
        String nickName,
        Set<AppraisalImage> appraisalImages,
        LocalDateTime createdAt,
        String createdBy
)  {

    public static ForAppraisalsResponse of(String productName, String content, String userEmail, String nickName,
                                          Set<AppraisalImage> appraisalImages, LocalDateTime createdAt, String createdBy) {
        return new ForAppraisalsResponse(productName, content, userEmail, nickName, appraisalImages, createdAt, createdBy);
    }

    public static ForAppraisalsResponse from(ForAppraisalsDto dto) {
        return new ForAppraisalsResponse(
                dto.productName(),
                dto.content(),
                dto.userAccountDto().userEmail(),
                dto.userAccountDto().nickName(),
                dto.appraisalImages(),
                dto.createdAt(),
                dto.createdBy()
        );
    }

}