package com.used.lux.service.user.appraisal;

import com.used.lux.component.FileHandler;
import com.used.lux.domain.*;
import com.used.lux.domain.appraisal.Appraisal;
import com.used.lux.domain.appraisal.AppraisalImage;
import com.used.lux.domain.appraisal.AppraisalRequest;
import com.used.lux.domain.appraisal.AppraisalRequestLog;
import com.used.lux.dto.user.appraisal.AppraisalDto;
import com.used.lux.dto.user.useraccount.UserAccountDto;
import com.used.lux.repository.*;
import com.used.lux.repository.appraisal.AppraisalImageRepository;
import com.used.lux.repository.appraisal.AppraisalRepository;
import com.used.lux.repository.appraisal.AppraisalRequestLogRepository;
import com.used.lux.repository.appraisal.AppraisalRequestRepository;
import com.used.lux.request.appraisal.AppraisalCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class AppraiseService {

    private final AppraisalRequestRepository appraisalRequestRepository;
    private final AppraisalRepository appraisalRepository;

    private final AppraisalImageRepository appraisalImageRepository;
    private final AppraisalRequestLogRepository appraisalRequestLogRepository;

    private final BrandRepository brandRepository;

    private final StateRepository stateRepository;

    private final FileHandler fileHandler;

    @Transactional(readOnly = true)
    public Page<AppraisalDto> findAllList(Pageable pageable) {
        return appraisalRepository.findAll(pageable).map(AppraisalDto::from);
    }

    @Transactional
    public void appraisalCreate(AppraisalCreateRequest request, UserAccountDto user) throws Exception {
        Brand brand = brandRepository.findById(request.brandId()).get();
        State state = stateRepository.findById(request.stateId()).get();

        AppraisalRequest appraisalRequest = appraisalRequestRepository.save(AppraisalRequest.of(
                request.productName(), brand, request.gender(), request.color(),
                request.size(), state, user.toEntity())); // 감정신청서 DB에 저장

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
        Appraisal appraisal = appraisalRepository.save(Appraisal.of(appraisalRequest));
        appraisalRequestLogRepository.save(AppraisalRequestLog.of(request.productName(),null,0,state, user.id(), appraisal.getId()));
    }

    public AppraisalDto appraisalDetail(Long appraisalId) {
        return AppraisalDto.from(appraisalRepository.findById(appraisalId).get());
    }

    public Page<AppraisalDto> getMypageAppraisal(Long id, Pageable pageable) {
        return appraisalRepository.findByUserAccountId(id, pageable).map(AppraisalDto::from);
    }


    //처리되지 않은 요청을 세는 메서드
    public Long countRequest() {
        return appraisalRequestRepository.countByState1();
    }

    public Appraisal findById(Long id) {
        Optional<Appraisal> appraisal = appraisalRepository.findById(id);
        return appraisal.orElse(null);
    }

    public Long findAppraisePriceByProductId(Long aLong) {
        return null;
        /*return appraisalRepository.findAppraisePriceByProductId(aLong);*/
    }

    public void apprisalDelete(Long appraisalRequestId) {
        appraisalRequestRepository.deleteById(appraisalRequestId);
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
