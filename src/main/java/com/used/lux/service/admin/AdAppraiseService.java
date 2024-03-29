package com.used.lux.service.admin;

import com.used.lux.domain.appraisal.Appraisal;
import com.used.lux.domain.appraisal.AppraisalResult;
import com.used.lux.domain.constant.AppraisalGrade;
import com.used.lux.domain.constant.AppraisalState;
import com.used.lux.dto.user.appraisal.AppraisalDto;
import com.used.lux.mapper.AppraisalMapper;
import com.used.lux.mapper.AppraisalResultMapper;
import com.used.lux.repository.*;
import com.used.lux.repository.appraisal.AppraisalRepository;
import com.used.lux.repository.appraisal.AppraisalResultRepository;
import com.used.lux.repository.appraisal.AppraisalRequestLogRepository;
import com.used.lux.repository.product.ProductRepository;
import com.used.lux.request.appraisal.AppraisalCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@RequiredArgsConstructor
@Service
public class AdAppraiseService {

    private final AppraisalRepository appRepo;
    private final AppraisalMapper appMapper;

    private final AppraisalResultRepository appResultRepo;
    private final AppraisalResultMapper appResultMapper;

    private final AppraisalRequestLogRepository appraisalRequestLogRepository;

    private final BrandRepository brandRepository;

    private final ProductRepository productRepository;

    private final CategoryBRepository categoryBRepository;

    private final CategoryMRepository categoryMRepository;

    public Page<AppraisalDto> getAppraiseList(Pageable pageable) {
        return appRepo.findByAppState(AppraisalState.SELL, pageable).map((item) -> {
                    if (item.getAppResultId() != null)
                        return appMapper.toDtoCustom(item, appResultRepo.findById(item.getAppResultId()).orElse(null));
                    else
                        return appMapper.toDtoCustom(item, null);
                }
        );
    }

    public Page<AppraisalDto> getAppraiseList(String appraisalState, String appraisalBrand, String appraisalGender,
                                                        String appraisalSize, String appraisalGrade, String appraisalDate,
                                                        String query, Pageable pageable) {
        return appRepo.searchAppraise(appraisalState, appraisalBrand, appraisalGender,
                appraisalSize, appraisalGrade, appraisalDate, query, pageable).map((item) -> {
                    if (item.getAppResultId() != null)
                        return appMapper.toDtoCustom(item, appResultRepo.findById(item.getAppResultId()).orElse(null));
                    else
                        return appMapper.toDtoCustom(item, null);
                }
        );
    }

    @Transactional
    public void appraiseComment(AppraisalCommentRequest request, Long appraisalId) {
        Appraisal appraisal = appRepo.getReferenceById(appraisalId);
        Long appraisalResultId = appResultRepo.save(AppraisalResult.builder()
                .appPrice(request.appraisalPrice())
                .appGrade(AppraisalGrade.valueOf(request.appraisalGrade()))
                .appComment(request.appraisalComment())
                .build()
        ).getId();

        appraisal.setAppState(AppraisalState.valueOf(request.appraisalState()));
        appraisal.setAppResultId(appraisalResultId);
//        appraisalRequestLogRepository.save(AppraisalRequestLog.of(
//                request.appraisalName(),request.appraisalGrade(), request.appraisalPrice(),
//                state, appraisalResult.getAppraisal().getUserAccount().getId(),appraisalId
//        ));
   }

    @Transactional
    public void appraiseChange(HashMap<String, String> data, Long appraisalId) {
        Appraisal appraisal = appRepo.getReferenceById(appraisalId);
        appraisal.setAppState(AppraisalState.valueOf(data.get("appraisalState")));
    }

    public AppraisalDto appraiseCommentPage(Long appraisalId) {
        Appraisal appraisal = appRepo.findById(appraisalId).get();
        if (appraisal.getAppResultId() != null)
            return appMapper.toDtoCustom(appraisal, appResultRepo.findById(appraisal.getAppResultId()).orElse(null));
        else
            return appMapper.toDtoCustom(appraisal, null);
    }
}
