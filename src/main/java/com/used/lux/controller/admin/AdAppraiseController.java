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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private final AdProductService adProductService;

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

        Page<AppraisalResponse> appraisalResponse = adAppraiseService.getAppraiseList(appraisalState,
                appraisalBrand, appraisalGender, appraisalSize, appraisalGrade, appraisalDate,
                query, pageable).map(AppraisalResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(),
                appraisalResponse.getTotalPages());
        SearchResponse searchResponse = searchService.getSearchList();

        mm.addAttribute("paginationBarNumbers", barNumbers);
        mm.addAttribute("appraisalResponse", appraisalResponse);
        mm.addAttribute("appraisalSearchResponse", searchResponse);
        return "/admin/appraise";
    }

    // 검수 결과 상세조회 페이지
    @GetMapping("/{appraisalId}")
    public String appraiseComment(@PathVariable Long appraisalId,
            @AuthenticationPrincipal Principal principal,
            ModelMap mm) {
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }

        AppraisalResponse appraisalResponse = AppraisalResponse
                .from(adAppraiseService.appraiseCommentPage(appraisalId));
        mm.addAttribute("appraisalResponse", appraisalResponse);
        return "/admin/appraisal-detail";
    }

    // 검수 결과 등록 페이지
    @GetMapping("/{appraisalId}/new")
    public String appraiseCommentAdd(@PathVariable Long appraisalId,
            @AuthenticationPrincipal Principal principal,
            ModelMap mm) {
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }

        AppraisalResponse appraisalResponse = AppraisalResponse
                .from(adAppraiseService.appraiseCommentPage(appraisalId));

        List<BrandDto> brandList = adProductService.getBrandList();
        mm.addAttribute("appraisalResponse", appraisalResponse);
        mm.addAttribute("brandList", brandList);

        return "admin/appraisal-create-form";
    }

    // 검수 결과 등록
    @ResponseBody
    @PostMapping("/{appraisalId}/new/loading")
    public ResponseEntity<Integer> appraiseCommentAdd(@PathVariable Long appraisalId,
            @AuthenticationPrincipal Principal principal,
            @RequestBody AppraisalCommentRequest appraisalCommentRequest) {

        if (principal.role().getName() != "ROLE_ADMIN") {
            return ResponseEntity.status(HttpStatus.OK).body(-1);
        }

        adAppraiseService.appraiseComment(appraisalCommentRequest, appraisalId);
        return ResponseEntity.status(HttpStatus.OK).body(1);
    }

}
