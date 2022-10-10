package com.used.lux.request;

import com.used.lux.domain.Brand;
import com.used.lux.dto.AppraisalDto;
import com.used.lux.dto.UserAccountDto;

public record AppraisalRequest(
        String productName,
        Long brnadId,
        String brandName,
        String content,
        String gender,
        String color,
        String size
)  {

    public static AppraisalRequest of(String productName, Long brnadId, String brandName, String content, String gender,
                                      String color, String size) {
        return new AppraisalRequest(productName, brnadId, brandName, content, gender, color, size);
    }

    public AppraisalDto toDto(UserAccountDto userAccountDto) {
        return AppraisalDto.of(
                productName,
                Brand.of(brnadId, brandName),
                content,
                gender,
                color,
                size,
                userAccountDto
        );
    }

}
