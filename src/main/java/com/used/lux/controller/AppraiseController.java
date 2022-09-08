package com.used.lux.controller;

import com.used.lux.dto.security.Principal;
import com.used.lux.request.ForAppraisalRequest;
import com.used.lux.response.appraisal.ForAppraisalResponse;
import com.used.lux.response.appraisal.ForAppraisalsResponse;
import com.used.lux.service.AppraiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AppraiseController {

    private AppraiseService appraiseService;

    // 감정 신청 리스트
    @GetMapping("/appraies")
    public ResponseEntity<Page<ForAppraisalsResponse>> appraisalList(
            @PageableDefault(size = 30, sort = "created_at", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<ForAppraisalsResponse> appraisalList = appraiseService.findAllList(pageable).map(ForAppraisalsResponse::from);
        return ResponseEntity.status(HttpStatus.OK).body(appraisalList);
    }

    // 감정 신청 상세정보
    @GetMapping("/appraies/detail/{id}")
    public ResponseEntity<ForAppraisalResponse> appraisalDetail(@PathVariable("id") String id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    // 감정 신청 페이지
    @GetMapping("/appraies/create")
    public ResponseEntity<Integer> appraisalCreate()
    {
        return ResponseEntity.status(HttpStatus.OK).body(1);
    }

    // 감정 신청
    @PostMapping("/appraies/create")
    public ResponseEntity<Integer> appraisalCreate(
            ForAppraisalRequest forAppraisalRequest,
            @AuthenticationPrincipal Principal principal
    ) throws Exception {
        appraiseService.create(forAppraisalRequest.toDto(principal.toDto()));
        return ResponseEntity.status(HttpStatus.OK).body(1);

    }

}
