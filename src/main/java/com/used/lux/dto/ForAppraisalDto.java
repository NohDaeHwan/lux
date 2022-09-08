package com.used.lux.dto;

import com.used.lux.domain.ForAppraisal;
import com.used.lux.domain.UserAccount;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public record ForAppraisalDto(
        Long id,
        String productName,
        String brandName,
        String content,
        String lived,
        String purchase,
        int purchasePrice,
        boolean whetherApprisal,
        UserAccountDto userAccountDto,
        List<MultipartFile> images,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
)  {

    public static ForAppraisalDto of(String productName, String brandName, String content, String lived,
                                     String purchase, int purchasePrice, boolean whetherApprisal,
                                     UserAccountDto userAccountDto, List<MultipartFile> images) {
        return new ForAppraisalDto(null, productName, brandName, content, lived, purchase, purchasePrice, whetherApprisal,
                userAccountDto, images, null, null, null, null);
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
