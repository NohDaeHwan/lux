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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                request.size(), state, user.toEntity())); // ??????????????? DB??? ??????

        List<AppraisalImage> imageList = fileHandler.parseAppraisalFileInfo(request, appraisalRequest); // ?????? ??????
        // ????????? ????????? ????????? ??????
        if(!imageList.isEmpty()) {
            for(AppraisalImage image : imageList) {
                // ????????? DB??? ??????
                System.out.println(image);
                appraisalImageRepository.save(image); // ????????? ?????? ??? ?????? DB??? ??????
            }
        }
        System.out.println("?????? ?????? ??????");
        Appraisal appraisal = appraisalRepository.save(Appraisal.of(appraisalRequest));
        appraisalRequestLogRepository.save(AppraisalRequestLog.of(request.productName(),null,0,state, user.id(), appraisal.getId()));
    }

    public AppraisalDto appraisalDetail(Long appraisalId) {
        return AppraisalDto.from(appraisalRepository.findById(appraisalId).get());
    }

    public Page<AppraisalDto> getMypageAppraisal(Long id, Pageable pageable) {
        return appraisalRepository.findByUserAccountId(id, pageable).map(AppraisalDto::from);
    }


    //???????????? ?????? ????????? ?????? ?????????
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

    @Transactional(readOnly = true)
    public List<AppraisalDto> productFind(String query) {
        return appraisalRepository.findByQuery(query).stream()
                .map(AppraisalDto::from).limit(8).collect(Collectors.toUnmodifiableList());
    }

    /*
    List<AppraisalImage> imgeList = fileHandler.parseFileInfo(dto, userAccount, appraisal); // ????????? ??????

    // ????????? ????????? ????????? ??????
        if(!imgeList.isEmpty()) {
        for(AppraisalImage appraisalImage : imgeList) {
            // ????????? DB??? ??????
            System.out.println(appraisalImage);
            appraiseImageRepository.save(appraisalImage); // ????????? ?????? ??? ?????? DB??? ??????
        }
    }
    */

}
