package com.used.lux.service.admin;

import com.used.lux.domain.appraisal.AppraisalRequest;
import com.used.lux.repository.appraisal.AppraisalRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AppraisalRequestService {

    private final AppraisalRequestRepository appraisalRequestRepository;

    public AppraisalRequest findById(Long id) {
        Optional<AppraisalRequest> appraisal = appraisalRequestRepository.findById(id);
        return appraisal.orElse(null);
    }

}
