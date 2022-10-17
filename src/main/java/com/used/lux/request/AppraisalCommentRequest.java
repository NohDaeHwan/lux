package com.used.lux.request;

public record AppraisalCommentRequest(
        String appraisalName,
        String appraiseBrand,
        String appraisalContent,
        String appraisalGender,
        String appraisalColor,
        String appraisalSize,
        String appraisalGrade,
        int appraisalPrice,
        String appraisalComment
)  {
    public static AppraisalCommentRequest of(String appraisalName, String appraiseBrand, String appraisalContent,
                                   String appraisalGender, String appraisalColor, String appraisalSize, String appraisalGrade,
                                   int appraisalPrice, String appraisalComment) {
        return new AppraisalCommentRequest(appraisalName, appraiseBrand, appraisalContent, appraisalGender, appraisalColor,
                appraisalSize, appraisalGrade, appraisalPrice, appraisalComment);
    }
}
