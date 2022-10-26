package com.used.lux.controller.user;

import com.used.lux.dto.security.Principal;
import com.used.lux.request.appraisal.AppraisalRequest;
import com.used.lux.response.appraisal.AppraisalResponse;
import com.used.lux.response.appraisal.AppraisalsResponse;
import com.used.lux.service.admin.AppraiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/appraisal")
@RestController
public class AppraiseController {

    private final AppraiseService appraiseService;

    // 감정 신청 리스트
    @GetMapping
    public ResponseEntity<Page<AppraisalsResponse>> appraisalList(
            @PageableDefault(size = 30, sort = "created_at", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<AppraisalsResponse> appraisalList = appraiseService.findAllList(pageable).map(AppraisalsResponse::from);
        return ResponseEntity.status(HttpStatus.OK).body(appraisalList);
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
