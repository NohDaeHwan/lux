package com.used.lux.service.admin;


import com.used.lux.domain.Auction;
import com.used.lux.domain.Product;
import com.used.lux.service.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DashBoardRequestService {
    private final ProductOrderService productOrderService;
    private final ProductOrderLogService productOrderLogService;

    private final UserAccountLogService userAccountLogService;

    private final UserWithDrawalService userWithDrawalService;

    private final UserGradeService userGradeService;
    private final AppraiseService appraiseService;
    private final ProductOrderCancleService productOrderCancleService;

    private final AuctionService auctionService;
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
        Long countRequestOrderCancle = productOrderCancleService.count();

        //주목할만한 경매 상품
        List<Auction> auctions = new ArrayList<Auction>();
        //종료임박 :: 기간내 가장 높은 가격 ::최근 유찰 :: 현재 경매중 가장 높은 가격 :: 가장 많은 입찰 횟수
        auctions.add(auctionService.auctionFindByCloseNearDate());
        auctions.add(auctionService.findByPrice(bannerDateType));
        auctions.add(auctionService.findByNearDateFailBid());
        auctions.add(auctionService.findByPriceWithState9());
        auctions.add(auctionService.findByMostBiddingWithState9());
        //주목할만한 유형 판매
        List<Product> products = new ArrayList<Product>();
        //브랜드별 :: 카테고리별 :: 가격별 :: 성별 :: 가장 많은 조회 수
        
        //최근 관리자 활동

        //예산보고


        mm.addAttribute("countOrder", countOrder);
        mm.addAttribute("sumPrice",sumPrice);
        mm.addAttribute("countCustomer",countCustomer);
        mm.addAttribute("countWithdrawal",countRequestWithdrawalCostomer);
        mm.addAttribute("countGrade",countUserGradeRequest);
        mm.addAttribute("countAppraise",countRequestAppraise);
        mm.addAttribute("countOrderNotTreat",countOrderNotTreat);
        mm.addAttribute("countCancle",countRequestOrderCancle);
        mm.addAttribute("auctionBanner",auctions);
        return mm;
    }

}
