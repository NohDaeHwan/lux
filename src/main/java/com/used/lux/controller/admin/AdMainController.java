package com.used.lux.controller.admin;

import com.used.lux.dto.admin.AdDashboardRequestDto;
import com.used.lux.service.admin.DashBoardRequestService;
import com.used.lux.dto.security.Principal;
import com.used.lux.response.UserAccountResponse;
import com.used.lux.service.admin.AdUserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdMainController {

    private final AdUserAccountService adUserAccountService;
    private final DashBoardRequestService dashBoardRequestService;
    // 메인 페이지
    @GetMapping
    public String test(@AuthenticationPrincipal Principal principal,
                       ModelMap mm) {
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }

        mm.addAllAttributes(dashBoardRequestService.pageCallRequest());

        return "/admin/index";
    }

    // 관리자 상세정보
    @GetMapping("/detail")
    public String userDetail(@AuthenticationPrincipal Principal principal,
                             ModelMap mm){
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }

        UserAccountResponse adminDetail = UserAccountResponse.from(adUserAccountService.getUserMemo(principal.id()));
        mm.addAttribute("adminDetail", adminDetail);
        return "/admin/admin-detail";
    }

    @PostMapping("/requestBannerChange")
    @ResponseBody
    public String bannerChange(@RequestBody AdDashboardRequestDto requestDto)
    {
        String response = "what?";
        if(requestDto.getBanner().equals("salesCard")){response = (String) dashBoardRequestService.requestSalesCard(requestDto.getBannerDateType());}
        else if(requestDto.getBanner().equals("revenueCard")){response = (String) dashBoardRequestService.requestRevenueCard(requestDto.getBannerDateType());}
        else if(requestDto.getBanner().equals("costomerCard")){response = (String) dashBoardRequestService.requestCostomerCard(requestDto.getBannerDateType());}
        else if(requestDto.getBanner().equals("recentSales")){response = (String) dashBoardRequestService.requestRecentSales(requestDto.getBannerDateType());}
        else if(requestDto.getBanner().equals("attentionPoint")){response = (String) dashBoardRequestService.requestAttentionpoint(requestDto.getBannerDateType());}
        else if(requestDto.getBanner().equals("costReport")){response = (String) dashBoardRequestService.requestCostReport(requestDto.getBannerDateType());}
        System.out.println(response);
        return response;
    }

}
