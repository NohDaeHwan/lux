package com.used.lux.controller.user;

import com.used.lux.dto.AppraisalDto;
import com.used.lux.dto.CategoryBDto;
import com.used.lux.dto.security.Principal;
import com.used.lux.request.appraisal.AppraisalRequest;
import com.used.lux.response.UserAccountResponse;
import com.used.lux.response.appraisal.AppraisalResponse;
import com.used.lux.response.appraisal.AppraisalsResponse;
import com.used.lux.service.CategoryBService;
import com.used.lux.service.UserAccountService;
import com.used.lux.service.admin.AppraiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private final UserAccountService userAccountService;

    // 감정 신청 리스트
    @GetMapping
    public String appraisalList(
            @PageableDefault(size = 30, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            ModelMap mm
    ) {
//        Page<AppraisalsResponse> appraisalList = appraiseService.findAllList(pageable).map(AppraisalsResponse::from);
//        return ResponseEntity.status(HttpStatus.OK).body(appraisalList);
        Page<AppraisalsResponse> appraisalList = appraiseService.findAllList(pageable).map(AppraisalsResponse::from);
        List<CategoryBDto> categoryList = categoryBService.categoryList();
        mm.addAttribute("categoryList", categoryList);
        mm.addAttribute("appraisalList",appraisalList);
        return "front/appraise";
    }


    // 감정 신청 리스트
    @GetMapping("/new")
    public String appraisalCreate(
            @PageableDefault(size = 30, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            ModelMap mm
    ) {
//        Page<AppraisalsResponse> appraisalList = appraiseService.findAllList(pageable).map(AppraisalsResponse::from);
//        return ResponseEntity.status(HttpStatus.OK).body(appraisalList);
        List<CategoryBDto> categoryList = categoryBService.categoryList();
        mm.addAttribute("categoryList", categoryList);
        return "front/appraise-create-form";
    }

    @GetMapping("/{appraiseId}")
    public String appraiseDetail(@PathVariable Long appraiseId,ModelMap mm) {
        AppraisalDto appraisalDto = appraiseService.appraisalDetail(appraiseId);
        List<CategoryBDto> categoryList = categoryBService.categoryList();
        mm.addAttribute("categoryList", categoryList);
        mm.addAttribute("appraisalDto",appraisalDto);
        return "front/appraise-detail";
    }

    // 감정 신청 상세정보
    @GetMapping("/detail/{id}")
    public ResponseEntity<AppraisalResponse> appraisalDetail(@PathVariable("id") String id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    // 감정 신청 페이지
    @GetMapping("/create")
    public ResponseEntity<Integer> appraisalCreate()
    {
        return ResponseEntity.status(HttpStatus.OK).body(1);
    }

    // 감정 신청
    @PostMapping("/create")
    public ResponseEntity<Integer> appraisalCreate(
            AppraisalRequest appraisalRequest,
            @AuthenticationPrincipal Principal principal
    ) throws Exception {
        appraiseService.create(appraisalRequest.toDto(principal.toDto()));
        return ResponseEntity.status(HttpStatus.OK).body(1);
    }

}
