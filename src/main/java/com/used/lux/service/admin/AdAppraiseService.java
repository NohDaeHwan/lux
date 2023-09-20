package com.used.lux.service.admin;

import com.used.lux.domain.*;
import com.used.lux.domain.appraisal.Appraisal;
import com.used.lux.domain.appraisal.AppraisalResult;
import com.used.lux.domain.constant.AppraisalGrade;
import com.used.lux.domain.constant.AppraisalState;
import com.used.lux.domain.constant.GenterType;
import com.used.lux.dto.user.appraisal.AppraisalDto;
import com.used.lux.dto.user.appraisal.AppraisalResultDto;
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

@RequiredArgsConstructor
@Service
public class AdAppraiseService {

    private final AppraisalRepository appRepo;
    private final AppraisalMapper appMapper;

    private final AppraisalResultRepository appResultRepo;
    private final AppraisalResultMapper appResultMapper;

    private final AppraisalRequestLogRepository appraisalRequestLogRepository;
    private final StateRepository stateRepository;

    private final BrandRepository brandRepository;

    private final ProductRepository productRepository;

    private final CategoryBRepository categoryBRepository;

    private final CategoryMRepository categoryMRepository;

    public Page<AppraisalDto> getAppraiseList(String appraisalState, String appraisalBrand, String appraisalGender,
                                                        String appraisalSize, String appraisalGrade, String appraisalDate,
                                                        String query, Pageable pageable) {
        return appRepo.searchAppraise(appraisalState, appraisalBrand, appraisalGender,
                appraisalSize, appraisalGrade, appraisalDate, query, pageable).map(appMapper::toDto);
    }

    @Transactional
    public void appraiseComment(AppraisalCommentRequest request, Long appraisalId) {
        Appraisal appraisal = appRepo.getReferenceById(appraisalId);
        AppraisalResult appraisalResult = appResultRepo.getReferenceById(appraisal.getAppResultId());
        Brand brand = brandRepository.findById(request.appraisalBrand()).get();
        appraisal.setAppState(AppraisalState.valueOf(request.appraisalState()));
        appraisal.setAppBrand(brand);
        appraisal.setAppGender(GenterType.valueOf(request.appraisalGender()));
        appraisal.setAppColor(request.appraisalColor());
        appraisal.setAppSize(request.appraisalSize());
        appraisal.setAppProdNm(request.appraisalName());


        appraisalResult.setAppGrade(AppraisalGrade.valueOf(request.appraisalGrade()));
        appraisalResult.setAppComment(request.appraisalComment());
        appraisalResult.setAppPrice(request.appraisalPrice());
//        appraisalRequestLogRepository.save(AppraisalRequestLog.of(
//                request.appraisalName(),request.appraisalGrade(), request.appraisalPrice(),
//                state, appraisalResult.getAppraisal().getUserAccount().getId(),appraisalId
//        ));
   }

    public AppraisalDto appraiseCommentPage(Long appraisalId) {
        return appMapper.toDto(appRepo.findById(appraisalId).get());
    }
}
