package com.used.lux.request;

public record AppraisalCommentRequest(
        String appraisalGrade,
        String appraisalComment,
        int appraisalPrice
)  {
    public static AppraisalCommentRequest of(String appraisalGrade, String appraisalComment, int appraisalPrice) {
        return new AppraisalCommentRequest(appraisalGrade, appraisalComment, appraisalPrice);
    }
}
