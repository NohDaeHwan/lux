package com.used.lux.service.admin;

import com.used.lux.domain.*;
import com.used.lux.dto.AppraisalDto;
import com.used.lux.repository.*;
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
        Appraisal result = appraisalRepository.save(appraisal);

        if (!result.getAppraisalGrade().equals("F")) {
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
