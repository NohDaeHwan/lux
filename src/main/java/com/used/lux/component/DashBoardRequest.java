package com.used.lux.component;


import com.used.lux.service.*;

import com.used.lux.service.admin.AppraiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@RequiredArgsConstructor
@Service
public class DashBoardRequest {
    private final ProductOrderService productOrderService;
    private final ProductOrderLogService productOrderLogService;

    private final UserAccountLogService userAccountLogService;

    private final UserWithDrawalService userWithDrawalService;

    private final UserGradeService userGradeService;
    private final AppraiseService appraiseService;
    private final ProductOrderCancleService productOrderCancleService;
    public ModelMap pageCallRequest()
    {
        ModelMap mm = new ModelMap();

        //오늘로 통일
        String bannerDateType = "today";
        //주문량
        Long countOrder = productOrderLogService.countOrderByDate(bannerDateType);
        //판매수익
        Long sumPrice =  productOrderLogService.countPriceByDate(bannerDateType);
        //방문고객
        Long countCustomer = userAccountLogService.countCustomerByDate(bannerDateType);

        //보고
        //탈퇴회원
        Long countRequestWithdrawalCostomer = userWithDrawalService.countAll();
        //승급회원
        Long countUserGradeRequest = userGradeService.countAll();
        //검수요청
        Long countRequestAppraise = appraiseService.countRequest();
        //주문내역
        Long countOrderNotTreat = productOrderLogService.countorderByState();
        //주문최소요청
        Long CountRequestOrderCancle = productOrderCancleService.count();
        //경매상품

        //주목해야 하는 상품

        //최근 관리자 상품

        //예산보고

        return mm;
    }

}
