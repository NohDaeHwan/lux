package com.used.lux.controller;

import com.used.lux.domain.Auction;
import com.used.lux.domain.AuctionOrder;
import com.used.lux.domain.ProductOrder;
import com.used.lux.domain.RentalHistory;
import com.used.lux.dto.security.Principal;
import com.used.lux.repository.AuctionOrderRepository;
import com.used.lux.repository.RentalHistoryRepository;
import com.used.lux.repository.UserAccountRepository;
import com.used.lux.response.ProductOrderResponse;
import com.used.lux.service.AuctionService;
import com.used.lux.service.RentalHistoryService;
import com.used.lux.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class UserAccountController {

    private UserAccountService userAccountService;

    private AuctionService auctionService;

    private RentalHistoryService rentalHistoryService;

    @GetMapping
    public String  Mypage(Model model, @AuthenticationPrincipal Principal principal, @PageableDefault(size = 2)Pageable pageable){

        //아이디를 사용해 로그인된 이용자의 구매 목록 나열
        Page<ProductOrder> orders = userAccountService.orderlistPage(principal.id(),pageable);



        return "/mypage";
    }


}
