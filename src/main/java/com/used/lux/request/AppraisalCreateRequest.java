package com.used.lux.request;

public record AppraisalCreateRequest(
        String appraisalProductName,
        String appraisalSize,
        String appraisalBrandName,
        String appraisalColor,
        String appraisalGender,
        String appraisalGrade,
        String appraisalComment,
        int appraisalPrice
) {
    public static AppraisalCreateRequest of(String appraisalProductName, String appraisalSize, String appraisalBrandName, String appraisalColor, String appraisalGender, String appraisalGrade, String appraisalComment, int appraisalPrice) {
        return new AppraisalCreateRequest(appraisalProductName,appraisalSize,appraisalBrandName,appraisalColor,appraisalGender,appraisalGrade,appraisalComment,appraisalPrice);
    }
}
