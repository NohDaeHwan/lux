package com.used.lux.controller.admin;

import com.used.lux.dto.BrandDto;
import com.used.lux.dto.security.Principal;
import com.used.lux.request.appraisal.AppraisalCommentRequest;
import com.used.lux.response.SearchResponse;
import com.used.lux.response.appraisal.AppraisalRequestResponse;
import com.used.lux.response.appraisal.AppraisalResponse;
import com.used.lux.service.PaginationService;
import com.used.lux.service.SearchService;
import com.used.lux.service.admin.AdAppraiseService;
import com.used.lux.service.admin.AdProductService;
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
@RequestMapping("/admin/appraise")
@Controller
public class AdAppraiseController {

    private final AdAppraiseService adAppraiseService;

    private  final AdProductService adProductService;

    private final PaginationService paginationService;

    private final SearchService searchService;

    // 검수기록
    @GetMapping
    public String appraise(@AuthenticationPrincipal Principal principal,
                           @PageableDefault(size = 30) Pageable pageable,
                           @RequestParam(defaultValue = "") String appraisalState,
                           @RequestParam(defaultValue = "") String appraisalBrand,
                           @RequestParam(defaultValue = "") String appraisalGender,
                           @RequestParam(defaultValue = "") String appraisalSize,
                           @RequestParam(defaultValue = "") String appraisalGrade,
                           @RequestParam(defaultValue = "2000-01-01") String appraisalDate,
                           @RequestParam(defaultValue = "") String query,
                           ModelMap mm) {
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }

        Page<AppraisalRequestResponse> appraisalResponse = adAppraiseService.getAppraiseList(appraisalState,
                appraisalBrand, appraisalGender, appraisalSize, appraisalGrade, appraisalDate,
                query, pageable).map(AppraisalRequestResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), appraisalResponse.getTotalPages());
        SearchResponse searchResponse = searchService.getSearchList();

        mm.addAttribute("paginationBarNumbers", barNumbers);
        mm.addAttribute("appraisalResponse", appraisalResponse);
        mm.addAttribute("appraisalSearchResponse", searchResponse);
        return "/admin/appraise";
    }

    // 검수 결과 등록 페이지
    @GetMapping("/new/{appraisalId}")
    public String appraiseComment(@PathVariable Long appraisalId,
                                  @AuthenticationPrincipal Principal principal) {
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }

        AppraisalRequestResponse appraisalResponse = AppraisalRequestResponse.from(adAppraiseService.appraiseCommentPage(appraisalId));
        return "/admin/appraisal-create-form";
    }

    // 검수 결과 등록 페이지
    @GetMapping("/{appraisalId}/new")
    public String appraiseComment(@PathVariable Long appraisalId,
                                  @AuthenticationPrincipal Principal principal,
                                  ModelMap mm) {
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }

        AppraisalRequestResponse appraisalResponse = AppraisalRequestResponse.from(adAppraiseService.appraiseCommentPage(appraisalId));
        List<BrandDto> brandList = adProductService.getBrandList();
        mm.addAttribute("appraisalResponse", appraisalResponse);
        mm.addAttribute("brandList",brandList);
        return "admin/appraisal-create-form";
    }

    //검수상세 수정 2022/10/26
    @GetMapping("/{appraisalId}/detail/update")
    public String appraiseDetail(@PathVariable Long appraisalId,
                                 @AuthenticationPrincipal Principal principal,
                                 ModelMap mm) {
        AppraisalRequestResponse appraisalResponse = AppraisalRequestResponse.from(adAppraiseService.appraiseCommentPage(appraisalId));
        List<BrandDto> brandList = adProductService.getBrandList();
        mm.addAttribute("appraisalResponse", appraisalResponse);
        mm.addAttribute("brandList",brandList);
        return "/admin/appraisal-detail";
    }

    // 검수 결과 등록
    @PostMapping("/{appraisalId}/new/update")
    public String appraiseCommentAdd(@PathVariable Long appraisalId,
                                     @AuthenticationPrincipal Principal principal,
                                     AppraisalCommentRequest appraisalCommentRequest) {

        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }

        System.out.println(appraisalCommentRequest);
        adAppraiseService.appraiseComment(appraisalCommentRequest, appraisalId);
        return "redirect:/admin/appraise";
    }



}
