package com.used.lux.controller.user;

import com.used.lux.dto.UserGradeDto;
import com.used.lux.dto.security.Principal;
import com.used.lux.dto.user.auction.AuctionMypageLogDto;
import com.used.lux.request.order.OrderCancelRequest;
import com.used.lux.request.useraccount.UserNameUpdateRequest;
import com.used.lux.request.useraccount.UserUpdateRequest;
import com.used.lux.response.order.ProductOrderResponse;
import com.used.lux.response.useraccount.UserAccountLogResponse;
import com.used.lux.response.useraccount.UserAccountResponse;
import com.used.lux.response.appraisal.AppraisalResponse;
import com.used.lux.service.*;
import com.used.lux.service.user.appraisal.AppraiseService;
import com.used.lux.service.user.auction.AuctionLogService;
import com.used.lux.service.user.order.ProductOrderCancelService;
import com.used.lux.service.user.order.ProductOrderService;
import com.used.lux.service.user.useraccount.UserAccountLogService;
import com.used.lux.service.user.useraccount.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/mypage")
@Controller
public class UserAccountController {

    private final UserAccountService userAccountService;

    private final ProductOrderService productOrderService;

    private final ProductOrderCancelService productOrderCancelService;

    private final UserAccountLogService userAccountLogService;
    private final AuctionLogService auctionLogService;

    private final UserGradeService userGradeService;

    private final AppraiseService appraiseService;

    private final PaginationService paginationService;

    // ??????????????????
    @GetMapping
    public String mypage(@AuthenticationPrincipal Principal principal, Pageable pageable, ModelMap mm) {
        UserAccountResponse userAccountResponse = UserAccountResponse.from(userAccountService.getUser(principal.id()));
        Page<ProductOrderResponse> productOrderResponse = productOrderService.productListAll(principal.id(), pageable)
                .map(ProductOrderResponse::from);
        UserGradeDto nextGrade = userGradeService.getNextGrade(principal.userGrade().getGradeStep());
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(),
                productOrderResponse.getTotalPages());
        Long totalPoint = userAccountLogService.getTotalPoint(principal.userEmail());

        mm.addAttribute("users", userAccountResponse);
        mm.addAttribute("orders", productOrderResponse);
        mm.addAttribute("nextGrade", nextGrade);
        mm.addAttribute("paginationBarNumbers", barNumbers);
        mm.addAttribute("total", totalPoint);

        return "/front/mypage-order";
    }

    // ??????????????????
    @PostMapping("/{orderId}/loading")
    public String mypageOrderDelete(@PathVariable Long orderId,
            @AuthenticationPrincipal Principal principal,
            OrderCancelRequest orderCancelRequest) {
        productOrderCancelService.orderCancel(principal, orderId, orderCancelRequest);

        return "redirect:/mypage/";
    }

    // ??????????????????
    @GetMapping("/profile-update")
    public String mypageProfileUpdate(@AuthenticationPrincipal Principal principal, ModelMap mm) {
        UserAccountResponse userAccountResponse = UserAccountResponse.from(userAccountService.getUser(principal.id()));

        mm.addAttribute("users", userAccountResponse);

        return "/front/profile-update";
    }

    // ????????????
    @GetMapping("/withdrawal")
    public String mypageWithdrawal(@AuthenticationPrincipal Principal principal, ModelMap mm) {
        UserAccountResponse userAccountResponse = UserAccountResponse.from(userAccountService.getUser(principal.id()));
        UserGradeDto nextGrade = userGradeService.getNextGrade(principal.userGrade().getGradeStep());
        Long totalPoint = userAccountLogService.getTotalPoint(principal.userEmail());

        mm.addAttribute("users", userAccountResponse);
        mm.addAttribute("nextGrade", nextGrade);
        mm.addAttribute("total", totalPoint);

        return "/front/mypage-withdrawal";
    }

    // ????????????
    @GetMapping("/auction")
    public String mypageAuction(@AuthenticationPrincipal Principal principal,
            ModelMap mm,
            @PageableDefault(size = 30, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        UserAccountResponse userAccountResponse = UserAccountResponse.from(userAccountService.getUser(principal.id()));
        UserGradeDto nextGrade = userGradeService.getNextGrade(principal.userGrade().getGradeStep());
        List<UserGradeDto> gradeList = userGradeService.getGradeList();
        List<AuctionMypageLogDto> auctionLogDtoPage = auctionLogService.searchAuctionLog(principal.userName(),pageable);
        Long totalPoint = userAccountLogService.getTotalPoint(principal.userEmail());

        mm.addAttribute("users", userAccountResponse);
        mm.addAttribute("nextGrade", nextGrade);
        mm.addAttribute("grades", gradeList);
        mm.addAttribute("auctionLog",auctionLogDtoPage);
        mm.addAttribute("total", totalPoint);

        return "/front/mypage-auction";
    }

    // ???????????????
    @GetMapping("/point")
    public String mypagePoint(@AuthenticationPrincipal Principal principal,
            ModelMap mm,
            @PageableDefault(size = 10, sort = "created_at", direction = Sort.Direction.DESC) Pageable pageable) {
        UserAccountResponse userAccountResponse = UserAccountResponse.from(userAccountService.getUser(principal.id()));
        UserGradeDto nextGrade = userGradeService.getNextGrade(principal.userGrade().getGradeStep());
        Page<UserAccountLogResponse> pointList = userAccountLogService.getPointList(principal.userEmail(), pageable)
                .map(UserAccountLogResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(),
                pointList.getTotalPages());
        Long totalPoint = userAccountLogService.getTotalPoint(principal.userEmail());

        mm.addAttribute("users", userAccountResponse);
        mm.addAttribute("nextGrade", nextGrade);
        mm.addAttribute("pointList", pointList);
        mm.addAttribute("paginationBarNumbers", barNumbers);
        mm.addAttribute("total", totalPoint);

        return "/front/mypage-point";
    }

    // ??????????????? ?????? ?????????
    @GetMapping("/point/new")
    public String mypagePointCreate(@AuthenticationPrincipal Principal principal, ModelMap mm) {
        UserAccountResponse userAccountResponse = UserAccountResponse.from(userAccountService.getUser(principal.id()));
        UserGradeDto nextGrade = userGradeService.getNextGrade(principal.userGrade().getGradeStep());
        Long totalPoint = userAccountLogService.getTotalPoint(principal.userEmail());

        mm.addAttribute("users", userAccountResponse);
        mm.addAttribute("nextGrade", nextGrade);
        mm.addAttribute("total",totalPoint);

        return "/front/mypage-point-create-form";
    }

    // ??????????????? ??????
    @PostMapping("/point/new/loading")
    public String mypagePointCreate(@AuthenticationPrincipal Principal principal,
            UserUpdateRequest userUpdateRequest) {
        userAccountService.userPointUpdate(principal, userUpdateRequest);
        return "redirect:/mypage/point";
    }

    // ????????????(????????????)
    @GetMapping("/mygrade")
    public String mypageGrade(@AuthenticationPrincipal Principal principal,
            ModelMap mm,
            @PageableDefault(size = 30, sort = "created_at", direction = Sort.Direction.DESC) Pageable pageable) {
        UserAccountResponse userAccountResponse = UserAccountResponse.from(userAccountService.getUser(principal.id()));
        UserGradeDto nextGrade = userGradeService.getNextGrade(principal.userGrade().getGradeStep());
        List<UserGradeDto> gradeList = userGradeService.getGradeList();
        Long totalPoint = userAccountLogService.getTotalPoint(principal.userEmail());

        mm.addAttribute("users", userAccountResponse);
        mm.addAttribute("nextGrade", nextGrade);
        mm.addAttribute("grades", gradeList);
        mm.addAttribute("total", totalPoint);

        return "/front/mypage-grade";
    }

    // ?????? ??? ??????
    @GetMapping("/recentview")
    public String recentview(@AuthenticationPrincipal Principal principal, Pageable pageable, ModelMap mm){

        UserAccountResponse userAccountResponse = UserAccountResponse.from(userAccountService.getUser(principal.id()));
        UserGradeDto nextGrade = userGradeService.getNextGrade(principal.userGrade().getGradeStep());
        List<UserGradeDto> gradeList = userGradeService.getGradeList();
        Page<ProductOrderResponse> productOrderResponse = productOrderService.productListAll(principal.id(), pageable)
                .map(ProductOrderResponse::from);
        Long totalPoint = userAccountLogService.getTotalPoint(principal.userEmail());

        mm.addAttribute("users", userAccountResponse);
        mm.addAttribute("orders", productOrderResponse);
        mm.addAttribute("nextGrade", nextGrade);
        mm.addAttribute("total", totalPoint);

        return "/front/mypage-recentview";
    }

    // ????????????
    @GetMapping("/appraise")
    public String appraise(@AuthenticationPrincipal Principal principal,
                           @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                           ModelMap mm) {
        UserAccountResponse userAccountResponse = UserAccountResponse.from(userAccountService.getUser(principal.id()));
        Page<AppraisalResponse> appraisalList = appraiseService.getMypageAppraisal(principal.id(), pageable).map(AppraisalResponse::from);
        UserGradeDto nextGrade = userGradeService.getNextGrade(principal.userGrade().getGradeStep());
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(),
                appraisalList.getTotalPages());
        Long totalPoint = userAccountLogService.getTotalPoint(principal.userEmail());

        mm.addAttribute("users", userAccountResponse);
        mm.addAttribute("appraisals", appraisalList);
        mm.addAttribute("nextGrade", nextGrade);
        mm.addAttribute("paginationBarNumbers", barNumbers);
        mm.addAttribute("total", totalPoint);

        return "/front/mypage-appraisal";
    }

    //?????? ??????
    @PostMapping("/updateform")
    public  String updateform(@AuthenticationPrincipal Principal principal, UserNameUpdateRequest userNameUpdateRequest){
        userAccountService.userNameUpdate(principal,userNameUpdateRequest);

        return "redirect:/mypage";
    }

    //?????? ??????
    @PostMapping("/deleteform")
    public  String deleteform(@AuthenticationPrincipal Principal principal){
        userAccountService.deleteUser(principal);

        return  "redirect:/";
    }

}
