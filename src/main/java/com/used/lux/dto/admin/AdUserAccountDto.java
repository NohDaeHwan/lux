package com.used.lux.dto.admin;

import com.used.lux.dto.user.appraisal.AppraisalDto;
import com.used.lux.dto.user.appraisal.AppraisalRequestLogDto;
import com.used.lux.dto.user.auction.AuctionLogDto;
import com.used.lux.dto.user.order.ProductOrderCancelDto;
import com.used.lux.dto.user.order.ProductOrderLogDto;
import com.used.lux.dto.user.useraccount.UserAccountDto;
import com.used.lux.dto.user.useraccount.UserAccountLogDto;

import java.util.List;

public record AdUserAccountDto(
        UserAccountDto user,
        List<ProductOrderLogDto> prodOrderLogList,
        List<ProductOrderCancelDto> prodOrderCancelList,
        List<AppraisalDto> appList,
        List<AuctionLogDto> aucLogList,
        List<UserAccountLogDto> userLogList,
        Long totalPoint
) {
    public static AdUserAccountDto of(UserAccountDto user, List<ProductOrderLogDto> prodOrderLogList,
                            List<ProductOrderCancelDto> prodOrderCancelList, List<AppraisalDto> appList,
                            List<AuctionLogDto> aucLogList, List<UserAccountLogDto> userLogList) {
        Long totalPoint = 0L;
        for (int i = 0; i < userLogList.size(); i++) {
            totalPoint += userLogList.get(i).point();
        }
        return new AdUserAccountDto(user, prodOrderLogList, prodOrderCancelList, appList, aucLogList, userLogList, totalPoint);
    }
}
