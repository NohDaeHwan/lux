package com.used.lux.dto.admin;

import com.used.lux.dto.*;

import java.util.List;

public record AdUserAccountDto(
        UserAccountDto userAccountDto,
        List<ProductOrderLogDto> productOrderLogDtos,
        List<ProductOrderCancelDto> productOrderCancelDtos,
        List<AppraisalDto> appraisalDtos,
        List<AuctionLogDto> auctionLogDtos,
        List<UserAccountLogDto> userAccountLogDtos,
        Long totalPoint
) {
    public static AdUserAccountDto of(UserAccountDto userAccountDto, List<ProductOrderLogDto> productOrderLogDtos,
                            List<ProductOrderCancelDto> productOrderCancelDtos, List<AppraisalDto> appraisalDtos,
                            List<AuctionLogDto> auctionLogDtos, List<UserAccountLogDto> userAccountLogDtos) {
        Long totalPoint = 0L;
        for (int i = 0; i < userAccountLogDtos.size(); i++) {
            totalPoint += userAccountLogDtos.get(i).point();
        }
        return new AdUserAccountDto(userAccountDto, productOrderLogDtos, productOrderCancelDtos,
                appraisalDtos, auctionLogDtos, userAccountLogDtos, totalPoint);
    }

}
