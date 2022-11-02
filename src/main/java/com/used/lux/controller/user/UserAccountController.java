package com.used.lux.controller.user;

import com.used.lux.dto.security.Principal;
import com.used.lux.response.ProductOrderResponse;
import com.used.lux.response.UserAccountResponse;
import com.used.lux.service.ProductOrderService;
import com.used.lux.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @GetMapping
    public String mypage(@AuthenticationPrincipal Principal principal,Pageable pageable,ModelMap mm){
        UserAccountResponse userAccountResponse = UserAccountResponse.from(userAccountService.getUser(principal.id()));
        Page<ProductOrderResponse> productOrderResponse = productOrderService.productListAll(principal.id(),pageable).map(ProductOrderResponse::from);
        mm.addAttribute("users",userAccountResponse);
        mm.addAttribute("orders",productOrderResponse);
        return "/front/mypage-order";
    }

    /*private final UserAccountService userAccountService;

    @GetMapping
    public String Mypage(ModelMap model, @AuthenticationPrincipal Principal principal, @PageableDefault(size = 2)Pageable pageable){
        //아이디를 사용해 로그인된 이용자의 구매 목록 나열
        Page<ProductOrderResponse> orders = userAccountService.orderlistPage(principal.toDto(), pageable).map(ProductOrderResponse::from);
        model.addAttribute("orders", orders);
        return "/mypage";
    }*/

}
