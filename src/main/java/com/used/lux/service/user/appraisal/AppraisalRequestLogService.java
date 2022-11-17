package com.used.lux.service.user.appraisal;

import com.used.lux.repository.appraisal.AppraisalRequestLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppraisalRequestLogService {
    private final AppraisalRequestLogRepository appraisalRequestLogRepository;

}
