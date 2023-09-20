package com.used.lux.service.admin;

import com.used.lux.domain.appraisal.Appraisal;
import com.used.lux.repository.appraisal.AppraisalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AppraisalRequestService {

    private final AppraisalRepository appraisalRepository;

    public Appraisal findById(Long id) {
        Optional<Appraisal> appraisal = appraisalRepository.findById(id);
        return appraisal.orElse(null);
    }

}
