package com.used.lux.controller.admin;

import com.used.lux.dto.BrandDto;
import com.used.lux.dto.CategoryBDto;
import com.used.lux.dto.security.Principal;
import com.used.lux.dto.user.appraisal.AppraisalDto;
import com.used.lux.request.appraisal.AppraisalCommentRequest;
import com.used.lux.service.BrandService;
import com.used.lux.service.CategoryBService;
import com.used.lux.service.PaginationService;
import com.used.lux.service.admin.AdAppraiseService;
import com.used.lux.service.admin.AdProductService;
import com.used.lux.service.user.product.ProductService;
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

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/admin/appraise")
@Controller
public class AdAppraiseController {

    private final AdAppraiseService adAppraiseService;

    private final AdProductService adProductService;

    private final PaginationService paginationService;

    private final CategoryBService categoryBService;

    private final ProductService productService;

    private final BrandService brandService;

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

        Page<AppraisalDto> appraisalList = adAppraiseService.getAppraiseList(appraisalState,
                appraisalBrand, appraisalGender, appraisalSize, appraisalGrade, appraisalDate,
                query, pageable);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(),
                appraisalList.getTotalPages());

        mm.addAttribute("paginationBarNumbers", barNumbers);
        mm.addAttribute("appraisalList", appraisalList);
        return "/admin/appraisal/appraisal-main";
    }

    // 검수 결과 상세조회 페이지
    @GetMapping("/{appraisalId}")
    public String appraiseComment(@PathVariable Long appraisalId,
            @AuthenticationPrincipal Principal principal,
            ModelMap mm) {
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }

        AppraisalDto appraisal = adAppraiseService.appraiseCommentPage(appraisalId);
        mm.addAttribute("appraisal", appraisal);
        return "/admin/appraisal/appraisal-detail";
    }

    // 검수 결과 등록 페이지
    @GetMapping("/{appraisalId}/new")
    public String appraiseCommentAdd(@PathVariable Long appraisalId,
            @AuthenticationPrincipal Principal principal,
            ModelMap mm) {
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }

        AppraisalDto appraisal = adAppraiseService.appraiseCommentPage(appraisalId);

        List<BrandDto> brandList = adProductService.getBrandList();
        mm.addAttribute("appraisal", appraisal);
        mm.addAttribute("brandList", brandList);

        return "/admin/appraisal/appraisal-create";
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

    // 검수 결과 등록
    @ResponseBody
    @PostMapping("/{appraisalId}/change")
    public ResponseEntity<Integer> appraiseCommentAdd(@PathVariable Long appraisalId,
                                                      @AuthenticationPrincipal Principal principal,
                                                      @RequestBody HashMap<String, String> data) {

        if (principal.role().getName() != "ROLE_ADMIN") {
            return ResponseEntity.status(HttpStatus.OK).body(-1);
        }

        adAppraiseService.appraiseChange(data, appraisalId);
        return ResponseEntity.status(HttpStatus.OK).body(1);
    }

    @GetMapping("/product/new")
    public String appraiseProductAddPage(@AuthenticationPrincipal Principal principal,
                                         @PageableDefault(size = 30) Pageable pageable,
                                         ModelMap mm) {
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }
        Page<AppraisalDto> appraisalList = adAppraiseService.getAppraiseList(pageable);
        List<BrandDto> brandList = brandService.brandList();
        List<CategoryBDto> cateBList = categoryBService.categoryList();
        List<String> gradeList = productService.gradeList();

        mm.addAttribute("appraisalList", appraisalList);
        mm.addAttribute("cateBList", cateBList);
        mm.addAttribute("gradeList", gradeList);
        mm.addAttribute("brandList", brandList);
        return "/admin/appraisal/product-create";
    }

    @ResponseBody
    @PostMapping("/product/new")
    public ResponseEntity<?> appraiseProductAdd(@AuthenticationPrincipal Principal principal,
                                                @PageableDefault(size = 30) Pageable pageable) {
        if (principal.role().getName() != "ROLE_ADMIN") {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("권한없음");
        }

        return ResponseEntity.status(HttpStatus.OK).body("성공");
    }
}
