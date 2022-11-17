package com.used.lux.service.admin;

import com.used.lux.domain.*;
import com.used.lux.domain.appraisal.AppraisalRequest;
import com.used.lux.domain.auction.Auction;
import com.used.lux.service.*;

import com.used.lux.service.user.appraisal.AppraiseService;
import com.used.lux.service.user.auction.AuctionService;
import com.used.lux.service.user.order.ProductOrderCancelService;
import com.used.lux.service.user.order.ProductOrderLogService;
import com.used.lux.service.user.useraccount.UserAccountLogService;
import com.used.lux.service.user.useraccount.UserWithDrawalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DashBoardRequestService {
    private final ProductOrderLogService productOrderLogService;

    private final UserAccountLogService userAccountLogService;

    private final UserWithDrawalService userWithDrawalService;

    private final UserGradeService userGradeService;
    private final AppraiseService appraiseService;

    private final AppraisalRequestService appraisalRequestService;
    private final ProductOrderCancelService productOrderCancelService;

    private final AuctionService auctionService;

    private final CategoryBService categoryBService;
    private final CategoryMService categoryMService;

    public ModelMap pageCallRequest()
    {
        ModelMap mm = new ModelMap();

        //오늘로 통일
        String bannerDateType = "today";
        //주문량
        Long countOrder = (Long) requestSalesCard(bannerDateType);
        //판매수익
        Long sumPrice = (Long) requestRevenueCard(bannerDateType);
        //방문고객
        Long countCustomer = (Long) requestCostomerCard(bannerDateType);

        //보고
        //탈퇴회원
        Long countRequestWithdrawalCostomer = userWithDrawalService.countAll();
        //승급회원
        Long countUserGradeRequest = userGradeService.countAll();
        //검수요청
        Long countRequestAppraise = appraiseService.countRequest();
        //주문내역
        Long countOrderNotTreat = productOrderLogService.countOrderByState();
        //주문최소요청
        Long countRequestOrderCancle = productOrderCancelService.count();

        //주목할만한 경매 상품
        List<Auction> auctions = new ArrayList<Auction>();
        auctions = (List<Auction>) requestRecentSales(bannerDateType);
        //주목할만한 판매 유형
        List<String> sellType = new ArrayList<String>();

        sellType = (List<String>) requestAttentionpoint(bannerDateType);

        //예산보고
        List<String> costReport = new ArrayList<>();
        //일간 주간 월간 판매 수익 판매와 경매 나누어서  :: 판매 순이익 계산  :: 경매 수수료를 통한 순이익 계산 :: 판매중인 상품 개수 :: 경매중인 상품 개수
        costReport = (List<String>) requestCostReport(bannerDateType);

        mm.addAttribute("countOrder", countOrder);
        mm.addAttribute("sumPrice",sumPrice);
        mm.addAttribute("countCustomer",countCustomer);
        mm.addAttribute("countWithdrawal",countRequestWithdrawalCostomer);
        mm.addAttribute("countGrade",countUserGradeRequest);
        mm.addAttribute("countAppraise",countRequestAppraise);
        mm.addAttribute("countOrderNotTreat",countOrderNotTreat);
        mm.addAttribute("countCancle",countRequestOrderCancle);
        mm.addAttribute("auctionBanner",auctions);
        mm.addAttribute("saleTypeBanner",sellType);
        mm.addAttribute("costReport",costReport);

        return mm;
    }

    public Long requestSalesCard(String bannerDateType)
    {
        return productOrderLogService.countOrderByDate(bannerDateType);
    }

    public Long requestRevenueCard(String bannerDateType)
    {
        return productOrderLogService.countPriceByDate(bannerDateType);
    }

    public Long requestCostomerCard(String bannerDateType)
    {
        return userAccountLogService.countCustomerByDate(bannerDateType);
    }

    public List<Auction> requestRecentSales(String bannerDateType)
    {
        //주목할만한 경매 상품
        List<Auction> auctions = new ArrayList<Auction>();
        Auction nullCol = new Auction();
        Auction result = new Auction();
        //종료임박 :: 기간내 가장 높은 가격 ::최근 유찰 :: 현재 경매중 가장 높은 가격 :: 가장 많은 입찰 횟수
        // 0: 종료임박
        result = auctionService.auctionFindByCloseNearDate();
        if(result != null){auctions.add(result); }
        else {auctions.add(nullCol);}
        // 1: 가장높은
        result = auctionService.findByPrice(bannerDateType);
        if(result != null){auctions.add(result); }
        else {auctions.add(nullCol);}
        // 2: 유찰
        result = auctionService.findByNearDateFailBid();
        if(result != null){auctions.add(result); }
        else {auctions.add(nullCol);}
        // 3: 현재 가장 높은
        result = auctionService.findByPriceWithState9();
        if(result != null){auctions.add(result); }
        else {auctions.add(nullCol);}
        // 4: 입찰횟수
        result = auctionService.findByMostBiddingWithState9();
        if(result != null){auctions.add(result); }
        else {auctions.add(nullCol);}

        return auctions;
    }

    public List<String> requestAttentionpoint(String bannerDateType)
    {
        //주목할만한 판매 유형
        List<String> sellType = new ArrayList<String>();

        //판매유형 :: 카테고리별(BIG.MIDDLE) :: 가격별 :: 가장 많은 조회 수
        String Bid = productOrderLogService.findByCategoryB(bannerDateType);
        String Mid = productOrderLogService.findByCategoryM(bannerDateType);
        String Aid = productOrderLogService.findByViewCount(bannerDateType);

        String sellRoute = productOrderLogService.findByState(bannerDateType);
        String categoryBName;
        String categoryMName;
        String priceRange = productOrderLogService.findByPriceRange(bannerDateType);
        String productName;

        CategoryB categoryB = null;
        CategoryM categoryM = null;
        AppraisalRequest appraisalRequest = null;

        //null처리 하면서 값 삽입 :: 이게 맞나?
        if(Bid != null){categoryB = categoryBService.findById(Long.valueOf(Bid));}

        if(Mid != null){categoryM = categoryMService.findById(Long.valueOf(Mid));}

        if(Aid != null){appraisalRequest = appraisalRequestService.findById(Long.valueOf(Aid));}

        if(sellRoute == null){sellRoute = "cannot search sell route because selling log is nowhere";}
        if(categoryB != null){ categoryBName = categoryB.getCategoryBName();}
        else{categoryBName = "cannot search categoryB because selling log is nowhere";}
        if(categoryM != null){categoryMName = categoryM.getCategoryMName(); }
        else {categoryMName = "cannot search categoryM because selling log is nowhere";}
        if(priceRange == null){priceRange = "cannot search price range because selling log is nowhere";}
        if(appraisalRequest != null){productName = appraisalRequest.getAppraisalProductName();}
        else {productName = "cannot search product because selling log is nowhere";}

        sellType.add(sellRoute); // 0: 판매유형
        sellType.add(categoryBName); // 1 : 카테고리B
        sellType.add(categoryMName);// 2 : 카테고리M
        sellType.add(priceRange); // 3 : 가격별
        sellType.add(productName); // 4 : 조회 수

        return sellType;
    }

    public List<String> requestCostReport(String bannerDateType)
    {
        //예산보고
        List<String> costReport = new ArrayList<>();
        //일간 주간 월간 판매 수익 판매와 경매 나누어서  :: 판매 순이익 계산  :: 경매 수수료를 통한 순이익 계산 :: 판매중인 상품 개수 :: 경매중인 상품 개수
        Long auction_profit = 0L;
        Long product_profit = 0L;
        Long date_profit = 0L;
        Long appraiseCostOfPurchasing  = 0L;
        Long product_net_profit = 0L;

        auction_profit = auctionService.sumProfitByDate(bannerDateType);
        product_profit = productOrderLogService.sumProfitByDate(bannerDateType);
        List<Long> product_date_sell_id = productOrderLogService.profitIdByDate(bannerDateType);

        if(auction_profit !=null || product_profit != null)
        {
            if(auction_profit != null) {date_profit+= auction_profit;}
            if(product_profit != null) {date_profit +=product_profit;}
        }
        if(product_date_sell_id.size() != 0) {
            for (Long aLong : product_date_sell_id) {appraiseCostOfPurchasing += appraiseService.findAppraisePriceByProductId(aLong);}
            product_net_profit = product_profit - appraiseCostOfPurchasing;
        }

        double auction_net_profit = product_net_profit*0.1;
        Long  selling_product = productOrderLogService.countSellingProduct();
        Long  progress_auction = productOrderLogService.countProgressAuction();

        costReport.add(String.valueOf(date_profit)); // 0 : 총수익
        costReport.add(String.valueOf(product_profit)); // 1 : 상품수익
        costReport.add(String.valueOf(auction_profit)); // 2 : 경매수익
        costReport.add(String.valueOf(product_net_profit)); // 3 : 상품순이익
        costReport.add(String.valueOf(auction_net_profit)); // 4 : 경매순이익
        costReport.add(String.valueOf(selling_product)); // 5 : 팔고있는 상품 개수
        costReport.add(String.valueOf(progress_auction)); // 6 : 경매중인 상품 개수

        return costReport;
    }
}
