package com.used.lux.controller.user;

import com.used.lux.domain.UserAccountLog;
import com.used.lux.dto.UserGradeDto;
import com.used.lux.dto.security.Principal;
import com.used.lux.request.OrderCancelRequest;
import com.used.lux.request.ProductUpdateRequest;
import com.used.lux.request.UserUpdateRequest;
import com.used.lux.response.ProductOrderResponse;
import com.used.lux.response.UserAccountLogResponse;
import com.used.lux.response.UserAccountResponse;
import com.used.lux.service.*;
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

    private final UserGradeService userGradeService;

    // 주문내역조회
    @GetMapping
    public String mypage(@AuthenticationPrincipal Principal principal, Pageable pageable, ModelMap mm) {
        UserAccountResponse userAccountResponse = UserAccountResponse.from(userAccountService.getUser(principal.id()));
        Page<ProductOrderResponse> productOrderResponse = productOrderService.productListAll(principal.id(), pageable)
                .map(ProductOrderResponse::from);
        UserGradeDto nextGrade = userGradeService.getNextGrade(principal.userGrade().getGradeStep());
        System.out.println(nextGrade);
        mm.addAttribute("users", userAccountResponse);
        mm.addAttribute("orders", productOrderResponse);
        mm.addAttribute("nextGrade", nextGrade);
        Long totalPoint = userAccountLogService.getTotalPoint(principal.userEmail());
        mm.addAttribute("total", totalPoint);
        return "/front/mypage-order";
    }

    // 주문취소요청
    @PostMapping("/{orderId}/loading")
    public String mypageOrderDelete(@PathVariable Long orderId,
            @AuthenticationPrincipal Principal principal,
            OrderCancelRequest orderCancelRequest) {
        productOrderCancelService.orderCancel(principal, orderId, orderCancelRequest);
        return "redirect:/mypage/";
    }

    // 회원정보변경
    @GetMapping("/profile-update")
    public String mypageProfileUpdate(@AuthenticationPrincipal Principal principal, ModelMap mm) {
        UserAccountResponse userAccountResponse = UserAccountResponse.from(userAccountService.getUser(principal.id()));
        mm.addAttribute("users", userAccountResponse);
        return "/front/profile-update";
    }

    // 회원탈퇴
    @GetMapping("/withdrawal")
    public String mypageWithdrawal(@AuthenticationPrincipal Principal principal, ModelMap mm) {
        UserAccountResponse userAccountResponse = UserAccountResponse.from(userAccountService.getUser(principal.id()));
        UserGradeDto nextGrade = userGradeService.getNextGrade(principal.userGrade().getGradeStep());
        mm.addAttribute("users", userAccountResponse);
        mm.addAttribute("nextGrade", nextGrade);
        Long totalPoint = userAccountLogService.getTotalPoint(principal.userEmail());
        mm.addAttribute("total", totalPoint);
        return "/front/mypage-withdrawal";
    }

    // 회원경매
    @GetMapping("/auction")
    public String mypageAuction(@AuthenticationPrincipal Principal principal,
            ModelMap mm,
            @PageableDefault(size = 30, sort = "created_at", direction = Sort.Direction.DESC) Pageable pageable) {
        UserAccountResponse userAccountResponse = UserAccountResponse.from(userAccountService.getUser(principal.id()));
        UserGradeDto nextGrade = userGradeService.getNextGrade(principal.userGrade().getGradeStep());
        List<UserGradeDto> gradelist = userGradeService.getGradeList();
        mm.addAttribute("users", userAccountResponse);
        mm.addAttribute("nextGrade", nextGrade);
        mm.addAttribute("grades", gradelist);
        Long totalPoint = userAccountLogService.getTotalPoint(principal.userEmail());
        mm.addAttribute("total", totalPoint);
        return "/front/mypage-auction";
    }

    // 회원포인트
    @GetMapping("/point")
    public String mypagePoint(@AuthenticationPrincipal Principal principal,
            ModelMap mm,
            @PageableDefault(size = 30, sort = "created_at", direction = Sort.Direction.DESC) Pageable pageable) {
        UserAccountResponse userAccountResponse = UserAccountResponse.from(userAccountService.getUser(principal.id()));
        UserGradeDto nextGrade = userGradeService.getNextGrade(principal.userGrade().getGradeStep());
        Page<UserAccountLogResponse> pointlist = userAccountLogService.getPointList("Barn@gmail.com", pageable)
                .map(UserAccountLogResponse::from);
        mm.addAttribute("users", userAccountResponse);
        mm.addAttribute("nextGrade", nextGrade);
        mm.addAttribute("points", pointlist);
        Long totalPoint = userAccountLogService.getTotalPoint(principal.userEmail());
        mm.addAttribute("total", totalPoint);
        return "/front/mypage-point";
    }

    // 회원포인트 충전
    @GetMapping("/point/new")
    public String mypagePointCreate(@AuthenticationPrincipal Principal principal, ModelMap mm) {
        UserAccountResponse userAccountResponse = UserAccountResponse.from(userAccountService.getUser(principal.id()));
        UserGradeDto nextGrade = userGradeService.getNextGrade(principal.userGrade().getGradeStep());
        mm.addAttribute("users", userAccountResponse);
        mm.addAttribute("nextGrade", nextGrade);
        Long totalPoint = userAccountLogService.getTotalPoint(principal.userEmail());
        mm.addAttribute("total",totalPoint);
        return "/front/mypage-point-create-form";
    }

    @PostMapping("/point/new/loading")
    public String mypagePointCreate(@AuthenticationPrincipal Principal principal,
            UserUpdateRequest userUpdateRequest) {
        System.out.println(userUpdateRequest);
        userAccountService.userPointUpdate(principal, userUpdateRequest);
        return "redirect:/mypage/point";
    }

    // 회원혜택(등급내역)
    @GetMapping("/mygrade")
    public String mypageGrade(@AuthenticationPrincipal Principal principal,
            ModelMap mm,
            @PageableDefault(size = 30, sort = "created_at", direction = Sort.Direction.DESC) Pageable pageable) {
        UserAccountResponse userAccountResponse = UserAccountResponse.from(userAccountService.getUser(principal.id()));
        UserGradeDto nextGrade = userGradeService.getNextGrade(principal.userGrade().getGradeStep());
        List<UserGradeDto> gradelist = userGradeService.getGradeList();
        mm.addAttribute("users", userAccountResponse);
        mm.addAttribute("nextGrade", nextGrade);
        mm.addAttribute("grades", gradelist);
        Long totalPoint = userAccountLogService.getTotalPoint(principal.userEmail());
        mm.addAttribute("total", totalPoint);
        return "/front/mypage-grade";
    }

    @GetMapping("/appraise")
    public String appraise(@AuthenticationPrincipal Principal principal, Pageable pageable, ModelMap mm) {
        UserAccountResponse userAccountResponse = UserAccountResponse.from(userAccountService.getUser(principal.id()));
        Page<ProductOrderResponse> productOrderResponse = productOrderService.productListAll(principal.id(), pageable)
                .map(ProductOrderResponse::from);
        UserGradeDto nextGrade = userGradeService.getNextGrade(principal.userGrade().getGradeStep());
        System.out.println(nextGrade);
        mm.addAttribute("users", userAccountResponse);
        mm.addAttribute("orders", productOrderResponse);
        mm.addAttribute("nextGrade", nextGrade);
        Long totalPoint = userAccountLogService.getTotalPoint(principal.userEmail());
        mm.addAttribute("total", totalPoint);
        return "/front/mypage-appraisal";
    }
    //
    // @GetMapping
    // public String Mypage(ModelMap model, @AuthenticationPrincipal Principal
    // principal, @PageableDefault(size = 2)Pageable pageable){
    // //아이디를 사용해 로그인된 이용자의 구매 목록 나열
    // Page<ProductOrderResponse> orders =
    // userAccountService.orderlistPage(principal.toDto(),
    // pageable).map(ProductOrderResponse::from);
    // model.addAttribute("orders", orders);
    // return "/mypage";
    // }


}
