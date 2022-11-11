package com.used.lux.request.appraisal;

public record AppraisalCommentRequest(
        String appraisalName,
        Long appraiseBrand,
        String appraisalGender,
        String appraisalColor,
        String appraisalSize,
        String appraisalGrade,
        int appraisalPrice,
        String appraisalComment
)  {
    public static AppraisalCommentRequest of(String appraisalName, Long appraiseBrand,
                                   String appraisalGender, String appraisalColor, String appraisalSize, String appraisalGrade,
                                   int appraisalPrice, String appraisalComment) {
        return new AppraisalCommentRequest(appraisalName, appraiseBrand, appraisalGender, appraisalColor,
                appraisalSize, appraisalGrade, appraisalPrice, appraisalComment);
    }
}
