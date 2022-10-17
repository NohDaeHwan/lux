package com.used.lux.controller.admin;

import com.used.lux.dto.BrandDto;
import com.used.lux.dto.UserGradeDto;
import com.used.lux.dto.UserWithdrawalDto;
import com.used.lux.dto.admin.AdUserAccountDto;
import com.used.lux.dto.security.Principal;
import com.used.lux.request.BrandCreateRequest;
import com.used.lux.request.GradeCreateRequest;
import com.used.lux.response.UserAccountResponse;
import com.used.lux.service.UserGradeService;
import com.used.lux.service.admin.AdUserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/admin/user")
@Controller
public class AdUserAccountController {

    private final AdUserAccountService adUserAccountService;
    private final UserGradeService userGradeService;

    // 회원 리스트
    @GetMapping
    public String userList(@AuthenticationPrincipal Principal principal,
                           @PageableDefault(size = 30) Pageable pageable,
                           ModelMap mm){
        /*if (principal == null) {
            return "redirect:/login";
        }
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }*/
        Page<UserAccountResponse> userList = adUserAccountService.getUserList(pageable).map(UserAccountResponse::from);
        mm.addAttribute("userList", userList);
        return "/admin/user";
    }

    // 회원 상세정보
    @GetMapping("/{userId}")
    public String userDetail(@PathVariable Long userId,
                             @AuthenticationPrincipal Principal principal,
                             ModelMap mm){
        /*if (principal == null) {
            return "redirect:/login";
        }
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }*/
        AdUserAccountDto userDetail = adUserAccountService.getUserDetail(userId);
        mm.addAttribute("userDetail", userDetail);
        return "/admin/user-detail";
    }

    // 회원 등급 종류 설정 페이지
    @GetMapping("/grade")
    public String userGrade(@AuthenticationPrincipal Principal principal,
                            ModelMap mm){
//        if (principal == null) {
//            return "redirect:/login";
//        }
//        if (principal.role().getName() != "ROLE_ADMIN") {
//            return "redirect:/";
//        }
        List<UserGradeDto> userGrades = adUserAccountService.getUserGrade();
        mm.addAttribute("userGrade", userGrades);
        return "/admin/user-grade";
    }

    @GetMapping("/grade/new")
    public String userGradeCreate(@AuthenticationPrincipal Principal principal){
        return "/admin/grade-create-form";
    }

    @PostMapping("/grade/new/create")
    public String userGradeCreate(@AuthenticationPrincipal Principal principal,
                                  GradeCreateRequest gradeCreateRequest,
                                  ModelMap mm){
        UserGradeDto userGradeDto = userGradeService.createGrade(gradeCreateRequest);
        mm.addAttribute("userGradeDto", userGradeDto);
        return "redirect:/admin/user/grade";
    }

    // 탈퇴 회원
    @GetMapping("/withdrawal")
    public String userWithdrawal(@AuthenticationPrincipal Principal principal,
                                ModelMap mm){
//        if (principal == null) {
//            return "redirect:/login";
//        }
//        if (principal.role().getName() != "ROLE_ADMIN") {
//            return "redirect:/";
//        }
        List<UserWithdrawalDto> userWithdrawals = adUserAccountService.getUserWithdrawal();
        mm.addAttribute("userWithdrawals", userWithdrawals);
        return "/admin/user-withdrawal";
    }

}
