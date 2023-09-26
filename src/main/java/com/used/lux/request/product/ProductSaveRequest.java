package com.used.lux.request.product;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record ProductSaveRequest(
        Long prodId,
        Long appraisalId,
        String prodSellType,
        String prodNm,
        Long cateBId,
        Long cateMId,
        Long prodBrandId,
        String prodSize,
        String prodGenderId,
        String prodColor,
        String prodGradeId,
        Long prodPrice,
        String prodContent,
        List<MultipartFile> images
) {
}
