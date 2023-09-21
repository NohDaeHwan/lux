package com.used.lux.request.appraisal;

public record AppraisalCommentRequest(
                String appraisalGrade,
                Long appraisalPrice,
                String appraisalComment,
                String appraisalState
) {
}
