package com.used.lux.controller.admin;

import com.used.lux.component.DashBoardRequest;
import com.used.lux.dto.security.Principal;
import com.used.lux.response.UserAccountResponse;
import com.used.lux.service.admin.AdUserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdMainController {

    private final AdUserAccountService adUserAccountService;
    private final DashBoardRequest dashBoardRequest;
    // 메인 페이지
    @GetMapping
    public String test(@AuthenticationPrincipal Principal principal,
                       ModelMap mm) {
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }

        dashBoardRequest.pageCallRequest();
        mm.addAttribute("hello","대시보드");
        return "/admin/index";
    }

    // 관리자 상세정보
    @GetMapping("/detail")
    public String userDetail(@AuthenticationPrincipal Principal principal,
                             ModelMap mm){
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }

        UserAccountResponse adminDetail = UserAccountResponse.from(adUserAccountService.getAdminDetail(principal.id()));
        mm.addAttribute("adminDetail", adminDetail);
        return "/admin/admin-detail";
    }

}
