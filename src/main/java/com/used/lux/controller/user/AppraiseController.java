package com.used.lux.controller.user;

import com.used.lux.dto.BrandDto;
import com.used.lux.dto.CategoryBDto;
import com.used.lux.dto.security.Principal;
import com.used.lux.dto.user.appraisal.AppraisalDto;
import com.used.lux.request.appraisal.AppraisalCreateRequest;
import com.used.lux.service.BrandService;
import com.used.lux.service.CategoryBService;
import com.used.lux.service.PaginationService;
import com.used.lux.service.user.appraisal.AppraiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/appraisal")
@Controller
public class AppraiseController {

    private final AppraiseService appraiseService;
    private final CategoryBService categoryBService;

    private final BrandService brandService;

    private final PaginationService paginationService;

    // 감정 신청 리스트
    @GetMapping
    public String appraisalList(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            ModelMap mm
    ) {
        Page<AppraisalDto> appraisalList = appraiseService.getList(pageable);
        List<CategoryBDto> cateBList = categoryBService.categoryList();
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(),
                appraisalList.getTotalPages());

        mm.addAttribute("cateBList", cateBList);
        mm.addAttribute("appraisalList", appraisalList);
        mm.addAttribute("paginationBarNumbers", barNumbers);

        return "front/appraisal/appraise";
    }


    // 감정 신청 페이지
    @GetMapping("/new")
    public String appraisalCreate(ModelMap mm) {
        List<BrandDto> brandList = brandService.brandList();
        List<CategoryBDto> cateBList = categoryBService.categoryList();

        mm.addAttribute("brandList", brandList);
        mm.addAttribute("cateBList", cateBList);
        return "front/appraisal/appraise-create-form";
    }

    // 감정 신청 페이지
    @PostMapping("/new/loading")
    public String appraisalCreateRequest(AppraisalCreateRequest request,
                                         @AuthenticationPrincipal Principal principal) throws Exception {
        System.out.println(request);
        appraiseService.appraisalCreate(request, principal.id());
        return "redirect:/appraisal";
    }

    // 검수 상세 조회
    @GetMapping("/{appraiseId}")
    public String appraiseDetail(@PathVariable Long appraiseId, ModelMap mm, @AuthenticationPrincipal Principal principal) {
        long loginId = 0L;
        if (principal != null) {
            loginId = principal.id();
       }

        AppraisalDto appraisal = appraiseService.appraisalDetail(appraiseId);
        List<CategoryBDto> cateBList = categoryBService.categoryList();

        mm.addAttribute("loginId", loginId);
        mm.addAttribute("cateBList", cateBList);
        mm.addAttribute("appraisal", appraisal);
        return "front/appraisal/appraise-detail";
    }

    // 검수 삭제
    @PostMapping("/{appraisalRequestId}/delete")
    public String appraiseDelete(@PathVariable Long appraisalRequestId) {
        appraiseService.apprisalDelete(appraisalRequestId);
        return "redirect:/admin/appraise";
    }

    @ResponseBody
    @PostMapping("/{appraisalId}/change")
    public ResponseEntity<Integer> appraiseCommentAdd(@PathVariable Long appraisalId,
                                                      @RequestBody HashMap<String, String> data) {
        appraiseService.appraiseChange(data, appraisalId);
        return ResponseEntity.status(HttpStatus.OK).body(1);
    }

}
