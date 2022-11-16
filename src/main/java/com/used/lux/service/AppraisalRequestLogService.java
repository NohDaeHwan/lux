package com.used.lux.service;

import com.used.lux.repository.AppraisalRequestLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppraisalRequestLogService {
    private final AppraisalRequestLogRepository appraisalRequestLogRepository;

}
