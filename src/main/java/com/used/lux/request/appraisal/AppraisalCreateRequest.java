package com.used.lux.request.appraisal;

import com.used.lux.domain.AppraisalRequestLog;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record AppraisalCreateRequest(
        String productName,
        Long brandId,
        String gender,
        String color,
        String size,
        Long stateId,
        List<MultipartFile> images
)  {

    public static AppraisalCreateRequest of(String productName, Long brandId, String gender,
                                            String color, String size, Long stateId, List<MultipartFile> images) {
        return new AppraisalCreateRequest(productName, brandId, gender, color, size, stateId, images);
    }

}
