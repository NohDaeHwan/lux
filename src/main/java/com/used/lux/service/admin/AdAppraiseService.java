package com.used.lux.service.admin;

import com.used.lux.domain.*;
import com.used.lux.domain.appraisal.Appraisal;
import com.used.lux.domain.appraisal.AppraisalRequestLog;
import com.used.lux.domain.product.Product;
import com.used.lux.dto.user.appraisal.AppraisalDto;
import com.used.lux.repository.*;
import com.used.lux.repository.appraisal.AppraisalRepository;
import com.used.lux.repository.appraisal.AppraisalRequestLogRepository;
import com.used.lux.repository.product.ProductRepository;
import com.used.lux.request.appraisal.AppraisalCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdAppraiseService {

    private final AppraisalRepository appraisalRepository;
    private final AppraisalRequestLogRepository appraisalRequestLogRepository;
    private final StateRepository stateRepository;

    private final BrandRepository brandRepository;

    private final ProductRepository productRepository;

    private final CategoryBRepository categoryBRepository;

    private final CategoryMRepository categoryMRepository;

    public Page<AppraisalDto> getAppraiseList(String appraisalState, String appraisalBrand, String appraisalGender,
                                              String appraisalSize, String appraisalGrade, String appraisalDate,
                                              String query, Pageable pageable) {
        return appraisalRepository.searchAppraise(appraisalState, appraisalBrand, appraisalGender,
                appraisalSize, appraisalGrade, appraisalDate, query, pageable).map(AppraisalDto::from);
    }

    public void appraiseComment(AppraisalCommentRequest request, Long appraisalId) {
        Appraisal appraisal = appraisalRepository.getReferenceById(appraisalId);
        State state = stateRepository.findById(request.appraisalStateId()).get();
        Brand brand = brandRepository.findById(request.appraisalBrand()).get();
        appraisal.getAppraisalRequest().setAppraisalState(state);
        appraisal.getAppraisalRequest().setAppraisalBrand(brand);
        appraisal.getAppraisalRequest().setAppraisalGender(request.appraisalGender());
        appraisal.getAppraisalRequest().setAppraisalColor(request.appraisalColor());
        appraisal.setAppraisalGrade(request.appraisalGrade());
        appraisal.setAppraisalComment(request.appraisalComment());
        appraisal.setAppraisalPrice(request.appraisalPrice());
        appraisal.getAppraisalRequest().setAppraisalSize(request.appraisalSize());
        appraisal.getAppraisalRequest().setAppraisalProductName(request.appraisalName());
        appraisalRequestLogRepository.save(AppraisalRequestLog.of(
                request.appraisalName(),request.appraisalGrade(), request.appraisalPrice(),
                state,appraisal.getAppraisalRequest().getUserAccount().getId(),appraisalId
        ));
        Appraisal result = appraisalRepository.save(appraisal);
        if (result.getAppraisalRequest().getAppraisalState().getStateStep().equals("검수중")) {} // 검수 임시 저장
        else if (!result.getAppraisalGrade().equals("F")) { // 검수 확정 후 등급이 F가 아니면 Product 생성
            CategoryB categoryB = categoryBRepository.findByOneCategory();
            CategoryM categoryM = categoryMRepository.findByOneCategory();
            State productState = stateRepository.findByStateStep("신규");
            productRepository.save(Product.of(result, categoryB, categoryM, productState, 0,
                    "", "", 0));
        }
   }

    public AppraisalDto appraiseCommentPage(Long appraisalId) {
        return AppraisalDto.from(appraisalRepository.findById(appraisalId).get());
    }


}
