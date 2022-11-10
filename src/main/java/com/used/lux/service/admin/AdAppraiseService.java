package com.used.lux.service.admin;

import com.used.lux.domain.Appraisal;
import com.used.lux.domain.AppraisalRequest;
import com.used.lux.domain.State;
import com.used.lux.dto.AppraisalRequestDto;
import com.used.lux.repository.AppraisalRepository;
import com.used.lux.repository.AppraisalRequestRepository;
import com.used.lux.repository.StateRepository;
import com.used.lux.request.appraisal.AppraisalCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdAppraiseService {

    private final AppraisalRequestRepository appraisalRequestRepository;

    private final AppraisalRepository appraisalRepository;

    private final StateRepository stateRepository;

    public Page<AppraisalRequestDto> getAppraiseList(String appraisalState, String appraisalBrand, String appraisalGender,
                                                     String appraisalSize, String appraisalGrade, String appraisalDate,
                                                     String query, Pageable pageable) {
        return appraisalRequestRepository.searchAppraise(appraisalState, appraisalBrand, appraisalGender,
                appraisalSize, appraisalGrade, appraisalDate, query, pageable).map(AppraisalRequestDto::from);
    }

    public void appraiseComment(AppraisalCommentRequest request, Long appraisalId) {
        AppraisalRequest appraisalRequest = appraisalRequestRepository.getReferenceById(appraisalId);
        State state = stateRepository.findById(3L).get();
        appraisalRepository.save(Appraisal.of(request.appraisalGrade(), request.appraisalComment(), request.appraisalPrice()));
        appraisalRequest.setAppraisalState(state);
        appraisalRequestRepository.save(appraisalRequest);
    }

    public AppraisalRequestDto appraiseCommentPage(Long appraisalId) {
        return AppraisalRequestDto.from(appraisalRequestRepository.findById(appraisalId).get());
    }

}
