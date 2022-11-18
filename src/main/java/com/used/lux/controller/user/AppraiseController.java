package com.used.lux.controller.user;

import com.used.lux.dto.BrandDto;
import com.used.lux.dto.CategoryBDto;
import com.used.lux.dto.security.Principal;
import com.used.lux.repository.response.UserAccountResponse;
import com.used.lux.request.appraisal.AppraisalCreateRequest;
import com.used.lux.response.useraccount.UserAccountResponse;
import com.used.lux.response.appraisal.AppraisalResponse;
import com.used.lux.service.BrandService;
import com.used.lux.service.CategoryBService;
import com.used.lux.service.user.useraccount.UserAccountService;
import com.used.lux.service.user.appraisal.AppraiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/appraisal")
@Controller
public class AppraiseController {

    private final AppraiseService appraiseService;
    private final CategoryBService categoryBService;

    private final BrandService brandService;

    private final UserAccountService userAccountService;

    // 감정 신청 리스트
    @GetMapping
    public String appraisalList(
            @PageableDefault(size = 30, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            ModelMap mm
    ) {
        Page<AppraisalResponse> appraisalList = appraiseService.findAllList(pageable).map(AppraisalResponse::from);
        List<CategoryBDto> categoryList = categoryBService.categoryList();

        mm.addAttribute("categoryList", categoryList);
        mm.addAttribute("appraisalList",appraisalList);
        return "front/appraise";
    }


    // 감정 신청 페이지
    @GetMapping("/new")
    public String appraisalCreate(ModelMap mm) {
        List<BrandDto> brandList = brandService.brandList();
        List<CategoryBDto> categoryList = categoryBService.categoryList();

        mm.addAttribute("brandList", brandList);
        mm.addAttribute("categoryList", categoryList);
        return "front/appraise-create-form";
    }

    // 감정 신청 페이지
    @PostMapping("/new/loading")
    public String appraisalCreateRequest(AppraisalCreateRequest request,
                                         @AuthenticationPrincipal Principal principal) throws Exception {
        System.out.println(request);
        appraiseService.appraisalCreate(request, principal.toDto());
        return "redirect:/appraisal";
    }

    // 검수 상세 조회
    @GetMapping("/{appraiseId}")
    public String appraiseDetail(@PathVariable Long appraiseId, ModelMap mm, @AuthenticationPrincipal Principal principal) {
        long loginId = 0L;
        if (principal != null) {
            loginId = principal.id();
       }

        UserAccountResponse userAccountResponse = UserAccountResponse.from(userAccountService.getUser(loginId));

        AppraisalResponse appraisalDto = AppraisalResponse.from(appraiseService.appraisalDetail(appraiseId));
        List<CategoryBDto> categoryList = categoryBService.categoryList();

        mm.addAttribute("users", userAccountResponse);
        mm.addAttribute("categoryList", categoryList);
        mm.addAttribute("appraisalDto",appraisalDto);
        return "front/appraise-detail";
    }

    // 검수 삭제
    @PostMapping("/{appraisalRequestId}/delete")
    public String appraiseDelete(@PathVariable Long appraisalRequestId) {
        appraiseService.apprisalDelete(appraisalRequestId);
        return "redirect:/admin/appraise";
    }

}
