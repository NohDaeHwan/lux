package com.used.lux.service;

import com.used.lux.domain.Appraisal;
import com.used.lux.domain.State;
import com.used.lux.dto.AppraisalDto;
import com.used.lux.repository.AppraisalRepository;
import com.used.lux.repository.StateRepository;
import com.used.lux.request.AppraisalCommentRequest;
import com.used.lux.response.appraisal.AppraisalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdAppraiseService {

    private final AppraisalRepository appraisalRepository;

    private final StateRepository stateRepository;

    public Page<AppraisalDto> getAppraiseList(Pageable pageable) {
        return appraisalRepository.findAll(pageable).map(AppraisalDto::from);
    }

    public void appraiseComment(Long appraisalId, AppraisalCommentRequest appraisalCommentRequest) {
        Appraisal appraisal = appraisalRepository.getReferenceById(appraisalId);
        State state = stateRepository.findById(3L).get();
        appraisal.setAppraisalGrade(appraisalCommentRequest.appraisalGrade());
        appraisal.setAppraisalState(state);
        appraisal.setAppraisalComment(appraisalCommentRequest.appraisalComment());
        appraisal.setAppraisalPrice(appraisalCommentRequest.appraisalPrice());
        appraisalRepository.save(appraisal);
    }

    public AppraisalDto appraiseCommentPage(Long appraisalId) {
        return AppraisalDto.from(appraisalRepository.findById(appraisalId).get());
    }

}
