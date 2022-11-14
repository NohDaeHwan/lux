package com.used.lux.request.appraisal;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AppraisalCommentRequest(
        String appraisalName,
        Long appraisalBrand,
        String appraisalGender,
        String appraisalColor,
        String appraisalSize,
        String appraisalGrade,
        int appraisalPrice,
        String appraisalComment,
        Long appraisalStateId
)  {
    public static AppraisalCommentRequest of(String appraisalName, Long appraisalBrand,
                                   String appraisalGender, String appraisalColor, String appraisalSize, String appraisalGrade,
                                             int appraisalPrice, String appraisalComment,Long stateId) {
        return new AppraisalCommentRequest(appraisalName, appraisalBrand, appraisalGender, appraisalColor,
                appraisalSize, appraisalGrade, appraisalPrice, appraisalComment,stateId);
    }
}
