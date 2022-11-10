package com.used.lux.request.appraisal;

import com.used.lux.dto.AppraisalRequestDto;
import com.used.lux.dto.BrandDto;
import com.used.lux.dto.StateDto;
import com.used.lux.dto.UserAccountDto;

public record AppraisalRequest(
        String productName,
        Long brnadId,
        String brandName,
        String gender,
        String color,
        String size,
        Long stateId,
        String stateName,
        String stateStep
)  {

    public static AppraisalRequest of(String productName, Long brnadId, String brandName, String gender,
                                      String color, String size, Long stateId, String stateName, String stateStep) {
        return new AppraisalRequest(productName, brnadId, brandName, gender, color, size, stateId, stateName, stateStep);
    }

    public AppraisalRequestDto toDto(UserAccountDto userAccountDto) {
        return AppraisalRequestDto.of(
                productName,
                BrandDto.of(brnadId, brandName),
                gender,
                color,
                size,
                StateDto.of(stateId, stateName, stateStep),
                userAccountDto
        );
    }

}
