package com.used.lux.controller.user;

import com.used.lux.dto.UserGradeDto;
import com.used.lux.dto.security.Principal;
import com.used.lux.response.ProductOrderResponse;
import com.used.lux.response.UserAccountLogResponse;
import com.used.lux.response.UserAccountResponse;
import com.used.lux.service.ProductOrderService;
import com.used.lux.service.UserAccountLogService;
import com.used.lux.service.UserAccountService;
import com.used.lux.service.UserGradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/mypage")
@Controller
public class UserAccountController {

    private final UserAccountService userAccountService;

    private final ProductOrderService productOrderService;

    private final UserAccountLogService userAccountLogService;

    private final UserGradeService userGradeService;

    @GetMapping
    public String mypage(@AuthenticationPrincipal Principal principal,Pageable pageable,ModelMap mm){
        UserAccountResponse userAccountResponse = UserAccountResponse.from(userAccountService.getUser(2L));
        Page<ProductOrderResponse> productOrderResponse = productOrderService.productListAll(2L,pageable).map(ProductOrderResponse::from);
        UserGradeDto nextGrade = userGradeService.getNextGrade(principal.userGrade().getGradeStep());

        System.out.println(nextGrade);
        mm.addAttribute("users",userAccountResponse);
        mm.addAttribute("orders",productOrderResponse);
        mm.addAttribute("nextGrade", nextGrade);
        return "/front/mypage-order";
    }

    // 회원정보변경
    @GetMapping("/profile-update")
    public String mypageProfileUpdate(@AuthenticationPrincipal Principal principal, ModelMap mm){
        UserAccountResponse userAccountResponse = UserAccountResponse.from(userAccountService.getUser(2L));
        mm.addAttribute("users",userAccountResponse);
        return "/front/profile-update";
    }

    // 회원탈퇴
    @GetMapping("/withdrawal")
    public String mypageWithdrawal(@AuthenticationPrincipal Principal principal, ModelMap mm){
        UserAccountResponse userAccountResponse = UserAccountResponse.from(userAccountService.getUser(2L));
        mm.addAttribute("users",userAccountResponse);
        return "/front/mypage-withdrawal";
    }
    // 회원포인트
    @GetMapping("/point")
    public String mypagePoint(@AuthenticationPrincipal Principal principal,
                              ModelMap mm,
                              @PageableDefault(size = 30,sort = "created_at", direction = Sort.Direction.DESC)
                                  Pageable pageable
                              ){
        Page<UserAccountLogResponse> pointlist = userAccountLogService.getPointList("Barn@gmail.com",pageable).map(UserAccountLogResponse::from);
        mm.addAttribute("pointlist",pointlist);
        return "/front/mypage-point";
    }

    /*@GetMapping
    public String Mypage(ModelMap model, @AuthenticationPrincipal Principal principal, @PageableDefault(size = 2)Pageable pageable){
        //아이디를 사용해 로그인된 이용자의 구매 목록 나열
        Page<ProductOrderResponse> orders = userAccountService.orderlistPage(principal.toDto(), pageable).map(ProductOrderResponse::from);
        model.addAttribute("orders", orders);
        return "/mypage";
    }*/

}
