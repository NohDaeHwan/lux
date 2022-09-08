package com.used.lux.request;

import com.used.lux.dto.ForAppraisalDto;
import com.used.lux.dto.UserAccountDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record ForAppraisalRequest(
        String productName,
        String brandName,
        String content,
        String lived,
        String purchase,
        int purchasePrice,
        List<MultipartFile> images
)  {

    public static ForAppraisalRequest of(String productName, String brandName, String content, String lived,
                                         String purchase, int purchasePrice, List<MultipartFile> images) {
        return new ForAppraisalRequest(productName, brandName, content, lived, purchase, purchasePrice, images);
    }

    public ForAppraisalDto toDto(UserAccountDto userAccountDto) {
        return ForAppraisalDto.of(
                productName,
                brandName,
                content,
                lived,
                purchase,
                purchasePrice,
                false,
                userAccountDto,
                images
        );
    }

}
