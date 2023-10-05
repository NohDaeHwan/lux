package com.used.lux.controller.admin;

import com.used.lux.dto.admin.AdDashboardRequestDto;
import com.used.lux.dto.admin.DashBoardResponseVO;
import com.used.lux.dto.user.useraccount.UserAccountDto;
import com.used.lux.service.admin.DashBoardRequestService;
import com.used.lux.dto.security.Principal;
import com.used.lux.response.useraccount.UserAccountResponse;
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

        UserAccountDto adminDetail = adUserAccountService.getUserMemo(principal.id());
        mm.addAttribute("adminDetail", adminDetail);
        return "/admin/admin-detail";
    }

    @PostMapping("/requestBannerChange")
    @ResponseBody
    public DashBoardResponseVO bannerChange(@RequestBody AdDashboardRequestDto requestDto)
    {

        DashBoardResponseVO dashBoardResponseVO = new DashBoardResponseVO();

        if(requestDto.getBanner().equals("salesCard"))
        {dashBoardResponseVO.setLResult(dashBoardRequestService.requestSalesCard(requestDto.getBannerDateType()));}
        else if(requestDto.getBanner().equals("revenueCard"))
        {dashBoardResponseVO.setLResult(dashBoardRequestService.requestRevenueCard(requestDto.getBannerDateType())); }
        else if(requestDto.getBanner().equals("costomerCard"))
        {dashBoardResponseVO.setLResult(dashBoardRequestService.requestCostomerCard(requestDto.getBannerDateType())); }
        else if(requestDto.getBanner().equals("recentSales"))
        {dashBoardResponseVO.setAuctions(dashBoardRequestService.requestRecentSales(requestDto.getBannerDateType())); }
        else if(requestDto.getBanner().equals("attentionPoint"))
        {dashBoardResponseVO.setStResult(dashBoardRequestService.requestAttentionpoint(requestDto.getBannerDateType()));}
        else if(requestDto.getBanner().equals("costReport"))
        {dashBoardResponseVO.setStResult(dashBoardRequestService.requestCostReport(requestDto.getBannerDateType()));}


        return dashBoardResponseVO;
    }

}
