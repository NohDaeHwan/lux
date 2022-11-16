package com.used.lux.controller.admin;

import com.used.lux.dto.UserAccountDto;
import com.used.lux.dto.UserGradeDto;
import com.used.lux.dto.UserWithdrawalDto;
import com.used.lux.dto.admin.AdUserAccountDto;
import com.used.lux.dto.security.Principal;
import com.used.lux.request.GradeCreateRequest;
import com.used.lux.request.UserMemoUpdateRequest;
import com.used.lux.repository.response.UserAccountResponse;
import com.used.lux.service.PaginationService;
import com.used.lux.service.UserGradeService;
import com.used.lux.service.admin.AdUserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/admin/user")
@Controller
public class AdUserAccountController {

    private final AdUserAccountService adUserAccountService;
    private final UserGradeService userGradeService;

    private final PaginationService paginationService;


    // 회원 리스트
    @GetMapping
    public String userList(@AuthenticationPrincipal Principal principal,
                           @PageableDefault(size = 30) Pageable pageable,
                           @RequestParam(defaultValue = "") String gender, @RequestParam(defaultValue = "") String age,
                           @RequestParam(defaultValue = "") String grade, @RequestParam(defaultValue = "2000-01-01") String date,
                           @RequestParam(defaultValue = "") String query,
                           ModelMap mm) {
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }

        System.out.println(gender + " " + age + " " + grade + " " + date + " " + query);
        Page<UserAccountResponse> userList = adUserAccountService.getUserList(pageable, gender, age, grade, date, query).map(UserAccountResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), userList.getTotalPages());

        List<UserGradeDto> userGradeList = userGradeService.getGradeList();
        System.out.println(barNumbers);
        mm.addAttribute("paginationBarNumbers", barNumbers);
        mm.addAttribute("userGradeList", userGradeList);
        mm.addAttribute("userList", userList);

        return "/admin/user";
    }

    // 회원 상세정보
    @GetMapping("/{userId}")
    public String userDetail(@PathVariable Long userId,
                             @AuthenticationPrincipal Principal principal,
                             ModelMap mm){
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }

        AdUserAccountDto userDetail = adUserAccountService.getUserDetail(userId);
        mm.addAttribute("userDetail", userDetail);
        return "/admin/user-detail";
    }

    // 회원 메모 추가 페이지
    @GetMapping("/{userId}/memo")
    public String userMemo(@PathVariable Long userId,
                           @AuthenticationPrincipal Principal principal,
                           ModelMap mm){
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }

        UserAccountDto userDetail = adUserAccountService.getUserMemo(userId);
        mm.addAttribute("userDetail", userDetail);
        return "/admin/user-memo-create-form";
    }

    // 회원 메모 추가
    @PostMapping("/{userId}/memo/update")
    public String userMemoCreate(@PathVariable Long userId,
                                 @AuthenticationPrincipal Principal principal,
                                 UserMemoUpdateRequest request,
                                 ModelMap mm){
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }

        adUserAccountService.updateMemo(userId, request);
        return "redirect:/admin/user/"+userId;
    }

    // 회원 등급 종류 설정 페이지
    @GetMapping("/grade")
    public String userGrade(@AuthenticationPrincipal Principal principal,
                            ModelMap mm){
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }

        List<UserGradeDto> userGrades = adUserAccountService.getUserGrade();
        mm.addAttribute("userGrade", userGrades);
        return "/admin/user-grade";
    }

    // 회원 등급 종류 추가 페이지
    @GetMapping("/grade/new")
    public String userGradeCreate(@AuthenticationPrincipal Principal principal){
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }

        return "/admin/grade-create-form";
    }

    // 회원 등급 종류 추가
    @PostMapping("/grade/new/create")
    public String userGradeCreate(@AuthenticationPrincipal Principal principal,
                                  GradeCreateRequest gradeCreateRequest,
                                  ModelMap mm){
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }

        UserGradeDto userGradeDto = userGradeService.createGrade(gradeCreateRequest);
        mm.addAttribute("userGradeDto", userGradeDto);
        return "redirect:/admin/user/grade";
    }

    // 회원 등급 종류 삭제
    @GetMapping("/grade/{gradeId}/delete")
    public String userGradeCreate(@PathVariable Long gradeId,
                                  @AuthenticationPrincipal Principal principal){
        userGradeService.deleteGrade(gradeId);
        return "redirect:/admin/user/grade";
    }

    // 탈퇴 회원
    @GetMapping("/withdrawal")
    public String userWithdrawal(@AuthenticationPrincipal Principal principal,
                                ModelMap mm){
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }

        List<UserWithdrawalDto> userWithdrawals = adUserAccountService.getUserWithdrawal();
        mm.addAttribute("userWithdrawals", userWithdrawals);
        return "/admin/user-withdrawal";
    }

}
