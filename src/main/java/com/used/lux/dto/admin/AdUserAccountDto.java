package com.used.lux.dto.admin;

import com.used.lux.dto.user.appraisal.AppraisalRequestDto;
import com.used.lux.dto.user.appraisal.AppraisalRequestLogDto;
import com.used.lux.dto.user.auction.AuctionLogDto;
import com.used.lux.dto.user.order.ProductOrderCancelDto;
import com.used.lux.dto.user.order.ProductOrderLogDto;
import com.used.lux.dto.user.useraccount.UserAccountDto;
import com.used.lux.dto.user.useraccount.UserAccountLogDto;

import java.util.List;

public record AdUserAccountDto(
        UserAccountDto userAccountDto,
        List<ProductOrderLogDto> productOrderLogDtos,
        List<ProductOrderCancelDto> productOrderCancelDtos,
        List<AppraisalRequestDto> appraisalRequestDtos,
        List<AuctionLogDto> auctionLogDtos,
        List<UserAccountLogDto> userAccountLogDtos,
        Long totalPoint,
        List<AppraisalRequestLogDto> appraisalRequestLogDtos
) {
    public static AdUserAccountDto of(UserAccountDto userAccountDto, List<ProductOrderLogDto> productOrderLogDtos,
                            List<ProductOrderCancelDto> productOrderCancelDtos, List<AppraisalRequestDto> appraisalRequestDtos,
                            List<AuctionLogDto> auctionLogDtos, List<UserAccountLogDto> userAccountLogDtos, List<AppraisalRequestLogDto> appraisalRequestLogDtos) {
        Long totalPoint = 0L;
        for (int i = 0; i < userAccountLogDtos.size(); i++) {
            totalPoint += userAccountLogDtos.get(i).point();
        }
        return new AdUserAccountDto(userAccountDto, productOrderLogDtos, productOrderCancelDtos,
                appraisalRequestDtos, auctionLogDtos, userAccountLogDtos, totalPoint, appraisalRequestLogDtos);
    }

}
