package com.used.lux.controller.admin;

import com.used.lux.dto.security.Principal;
import com.used.lux.request.AppraisalCommentRequest;
import com.used.lux.response.appraisal.AppraisalResponse;
import com.used.lux.service.AdAppraiseService;
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

@RequiredArgsConstructor
@RequestMapping("/admin/appraise")
@Controller
public class AdAppraiseController {

    private final AdAppraiseService adAppraiseService;

    // 검수기록
    @GetMapping
    public String appraise(@AuthenticationPrincipal Principal principal,
                           @PageableDefault(size = 30) Pageable pageable,
                           ModelMap mm) {
//        if (principal == null) {
//            return "redirect:/login";
//        }
//        if (principal.role().getName() != "ROLE_ADMIN") {
//            return "redirect:/";
//        }
        Page<AppraisalResponse> appraisalResponse = adAppraiseService.getAppraiseList(pageable).map(AppraisalResponse::from);
        mm.addAttribute("appraisalResponse", appraisalResponse);
        return "/admin/appraise";
    }

    // 검수 결과 등록 페이지
    @GetMapping("/new/{appraisalId}")
    public String appraiseComment(@PathVariable Long appraisalId,
                                  @AuthenticationPrincipal Principal principal) {
//        if (principal == null) {
//            return "redirect:/login";
//        }
//        if (principal.role().getName() != "ROLE_ADMIN") {
//            return "redirect:/";
//        }
        AppraisalResponse appraisalResponse = AppraisalResponse.from(adAppraiseService.appraiseCommentPage(appraisalId));
        return "/admin/appraisal-create-form";
    }

    // 검수 결과 등록
    @PostMapping("/new/{appraisalId}")
    public String appraiseCommentAdd(@PathVariable Long appraisalId,
                                  @AuthenticationPrincipal Principal principal,
                                  AppraisalCommentRequest appraisalCommentRequest) {
//        if (principal == null) {
//            return "redirect:/login";
//        }
//        if (principal.role().getName() != "ROLE_ADMIN") {
//            return "redirect:/";
//        }
        adAppraiseService.appraiseComment(appraisalId, appraisalCommentRequest);
        return "/admin/appraise";
    }

}
