package com.used.lux.service.admin;

import com.used.lux.domain.Appraisal;
import com.used.lux.domain.State;
import com.used.lux.dto.AppraisalDto;
import com.used.lux.repository.AppraisalRepository;
import com.used.lux.repository.StateRepository;
import com.used.lux.request.appraisal.AppraisalCommentRequest;
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



    //업데이트

    public void appraiseComment(AppraisalCommentRequest appraisalCommentRequest, Long appraisalId) {
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
