package com.used.lux.service.admin;

import com.used.lux.component.FileHandler;
import com.used.lux.domain.*;
import com.used.lux.dto.AppraisalRequestDto;
import com.used.lux.dto.UserAccountDto;
import com.used.lux.repository.*;
import com.used.lux.request.appraisal.AppraisalCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class AppraiseService {

    private final AppraisalRequestRepository appraisalRequestRepository;

    private final AppraisalImageRepository appraisalImageRepository;

    private final BrandRepository brandRepository;

    private final StateRepository stateRepository;

    private final FileHandler fileHandler;

    @Transactional(readOnly = true)
    public Page<AppraisalRequestDto> findAllList(Pageable pageable) {
        return appraisalRequestRepository.findAll(pageable).map(AppraisalRequestDto::from);
    }

    @Transactional
    public void appraisalCreate(AppraisalCreateRequest request, UserAccountDto user) throws Exception {
        Brand brand = brandRepository.findById(request.brandId()).get();
        State state = stateRepository.findById(request.stateId()).get();
        AppraisalRequest appraisalRequest = appraisalRequestRepository.save(AppraisalRequest.of(
                request.productName(), brand, request.gender(), request.color(), request.size(), state, user.toEntity()
        )); // 감정신청서 DB에 저장

        List<AppraisalImage> imageList = fileHandler.parseAppraisalFileInfo(request, appraisalRequest); // 파일 처리
        // 파일이 존재할 때에만 처리
        if(!imageList.isEmpty()) {
            for(AppraisalImage image : imageList) {
                // 파일을 DB에 저장
                System.out.println(image);
                appraisalImageRepository.save(image); // 이미지 이름 및 경로 DB에 저장
            }
        }
        System.out.println("파일 저장 성공");
    }

    public AppraisalRequestDto appraisalDetail(Long productId) {
        return AppraisalRequestDto.from(appraisalRequestRepository.findById(productId).get());
    }

    /*
    List<AppraisalImage> imgeList = fileHandler.parseFileInfo(dto, userAccount, appraisal); // 이미지 처리

    // 파일이 존재할 때에만 처리
        if(!imgeList.isEmpty()) {
        for(AppraisalImage appraisalImage : imgeList) {
            // 파일을 DB에 저장
            System.out.println(appraisalImage);
            appraiseImageRepository.save(appraisalImage); // 이미지 이름 및 경로 DB에 저장
        }
    }
    */

}
