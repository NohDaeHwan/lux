package com.used.lux.service.admin;

import com.used.lux.domain.Appraisal;
import com.used.lux.domain.AppraisalRequest;
import com.used.lux.domain.Brand;
import com.used.lux.domain.State;
import com.used.lux.dto.AppraisalDto;
import com.used.lux.dto.AppraisalRequestDto;
import com.used.lux.repository.AppraisalRepository;
import com.used.lux.repository.AppraisalRequestRepository;
import com.used.lux.repository.BrandRepository;
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
    private final BrandRepository brandRepository;

    public Page<AppraisalDto> getAppraiseList(String appraisalState, String appraisalBrand, String appraisalGender,
                                              String appraisalSize, String appraisalGrade, String appraisalDate,
                                              String query, Pageable pageable) {
        return appraisalRepository.searchAppraise(appraisalState, appraisalBrand, appraisalGender,
                appraisalSize, appraisalGrade, appraisalDate, query, pageable).map(AppraisalDto::from);
    }

    public void appraiseComment(AppraisalCommentRequest request, Long appraisalId) {
        Appraisal appraisal = appraisalRepository.getReferenceById(appraisalId);
        State state = stateRepository.findById(request.stateId()).get();
        Brand brand = brandRepository.findById(request.appraiseBrand()).get();

        appraisal.getAppraisalRequest().setAppraisalState(state);
        appraisal.getAppraisalRequest().setAppraisalBrand(brand);
        appraisal.getAppraisalRequest().setAppraisalGender(request.appraisalGender());
        appraisal.getAppraisalRequest().setAppraisalColor(request.appraisalColor());
        appraisal.setAppraisalGrade(request.appraisalGrade());
        appraisal.setAppraisalComment(request.appraisalComment());
        appraisal.setAppraisalPrice(request.appraisalPrice());
        appraisal.getAppraisalRequest().setAppraisalSize(request.appraisalSize());
        appraisal.getAppraisalRequest().setAppraisalProductName(request.appraisalName());
        appraisalRepository.save(appraisal);
    }

    public AppraisalDto appraiseCommentPage(Long appraisalId) {
        Appraisal appraisal = appraisalRepository.getReferenceById(appraisalId);
        State state = stateRepository.findById(2L).get();
        appraisal.getAppraisalRequest().setAppraisalState(state);
        return AppraisalDto.from(appraisalRepository.save(appraisal));
    }


}
