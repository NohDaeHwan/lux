package com.used.lux.controller.user;

import com.used.lux.dto.CategoryMDto;
import com.used.lux.dto.user.appraisal.AppraisalDto;
import com.used.lux.service.CategoryMService;
import com.used.lux.service.user.appraisal.AppraiseService;
import com.used.lux.service.user.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommonController {

    private final CategoryMService cateMService;

    private final ProductService productService;

    private final AppraiseService appraiseService;

    @PostMapping("/cate/{cateBId}")
    public ResponseEntity<?> cateListSearch(@PathVariable Long cateBId) {
        List<CategoryMDto> cateList = cateMService.cateList(cateBId);
        System.out.println(cateList);
        return ResponseEntity.status(HttpStatus.OK).body(cateList);
    }

    @PostMapping("/appraisal/{appraisalId}")
    public ResponseEntity<?> getAppraisal(@PathVariable Long appraisalId) {
        AppraisalDto appList = appraiseService.getAppraisal(appraisalId);
        System.out.println(appList);
        return ResponseEntity.status(HttpStatus.OK).body(appList);
    }
}
