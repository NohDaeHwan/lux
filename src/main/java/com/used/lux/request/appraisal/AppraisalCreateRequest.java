package com.used.lux.request.appraisal;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record AppraisalCreateRequest(
        String productName,
        Long brandId,
        String gender,
        String color,
        String size,
        List<MultipartFile> images
)  {
}
