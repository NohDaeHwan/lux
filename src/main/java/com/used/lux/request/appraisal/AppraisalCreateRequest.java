package com.used.lux.request.appraisal;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record AppraisalCreateRequest(
        String productName,
        Long brandId,
        String gender,
        String color,
        String size,
        String state,
        List<MultipartFile> images
)  {

    public static AppraisalCreateRequest of(String productName, Long brandId, String gender,
                                            String color, String size, String state, List<MultipartFile> images) {
        return new AppraisalCreateRequest(productName, brandId, gender, color, size, state, images);
    }

}
