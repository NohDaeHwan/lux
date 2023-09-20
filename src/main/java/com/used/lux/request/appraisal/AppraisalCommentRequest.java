package com.used.lux.request.appraisal;

public record AppraisalCommentRequest(
                String appraisalName,
                Long appraisalBrand,
                String appraisalGender,
                String appraisalColor,
                String appraisalSize,
                String appraisalGrade,
                Long appraisalPrice,
                String appraisalComment,
                String appraisalState) {
        public static AppraisalCommentRequest of(String appraisalName, Long appraisalBrand,
                        String appraisalGender, String appraisalColor, String appraisalSize, String appraisalGrade,
                                                 Long appraisalPrice, String appraisalComment, String appraisalState) {
                return new AppraisalCommentRequest(appraisalName, appraisalBrand, appraisalGender, appraisalColor,
                                appraisalSize, appraisalGrade, appraisalPrice, appraisalComment, appraisalState);
        }
}
