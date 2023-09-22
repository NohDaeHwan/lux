package com.used.lux.service.user.appraisal;

import com.used.lux.component.FileHandler;
import com.used.lux.domain.*;
import com.used.lux.domain.appraisal.Appraisal;
import com.used.lux.domain.appraisal.AppraisalResult;
import com.used.lux.domain.appraisal.AppraisalImage;
import com.used.lux.domain.constant.AppraisalState;
import com.used.lux.domain.constant.GenterType;
import com.used.lux.domain.useraccount.UserAccount;
import com.used.lux.dto.user.appraisal.AppraisalDto;
import com.used.lux.mapper.AppraisalImageMapper;
import com.used.lux.mapper.AppraisalMapper;
import com.used.lux.mapper.AppraisalResultMapper;
import com.used.lux.repository.*;
import com.used.lux.repository.appraisal.AppraisalImageRepository;
import com.used.lux.repository.appraisal.AppraisalResultRepository;
import com.used.lux.repository.appraisal.AppraisalRequestLogRepository;
import com.used.lux.repository.appraisal.AppraisalRepository;
import com.used.lux.repository.useraccount.UserAccountRepository;
import com.used.lux.request.appraisal.AppraisalCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class AppraiseService {

    private final AppraisalRepository appRepo;
    private final AppraisalMapper appMapper;

    private final AppraisalResultRepository appResultRepo;
    private final AppraisalResultMapper appResultMapper;

    private final AppraisalImageRepository appImageRepo;
    private final AppraisalImageMapper appImageMapper;


    private final AppraisalRequestLogRepository appraisalRequestLogRepository;

    private final BrandRepository brandRepository;

    private final StateRepository stateRepository;

    private final UserAccountRepository userAccountRepo;

    private final FileHandler fileHandler;

    @Transactional(readOnly = true)
    public Page<AppraisalDto> getList(Pageable pageable) {
        return appRepo.findAll(pageable).map((item) -> {
            if (item.getAppResultId() != null)
                return appMapper.toDtoCustom(item, appResultRepo.findById(item.getAppResultId()).orElse(null));
            else
                return appMapper.toDtoCustom(item, null);
        });
    }

    @Transactional
    public void appraisalCreate(AppraisalCreateRequest request, Long userId) throws Exception {
        UserAccount userAccount = userAccountRepo.findById(userId).orElse(null);
        Brand brand = brandRepository.findById(request.brandId()).orElse(null);

        Appraisal appraisal = appRepo.save(Appraisal.builder()
                .appProdNm(request.productName())
                .appBrand(brand)
                .appGender(GenterType.valueOf(request.gender()))
                .appColor(request.color())
                .appSize(request.size())
                .appState(AppraisalState.BEFORE)
                .appResultId(null)
                .userAccount(userAccount)
                .build()); // 감정신청서 DB에 저장

        List<AppraisalImage> imageList = fileHandler.parseAppraisalFileInfo(request, appraisal); // 파일 처리
        // 파일이 존재할 때에만 처리
        if(!imageList.isEmpty()) {
            // 이미지 이름 및 경로 DB에 저장
            appImageRepo.saveAll(imageList);
        }
        System.out.println("파일 저장 성공");
//        AppraisalResult appraisalResult = appraisalResultRepository.save(AppraisalResult.of(appraisal));
//        appraisalRequestLogRepository.save(AppraisalRequestLog.of(request.productName(),null,0,state, user.id(), appraisalResult.getId()));
    }

    public AppraisalDto appraisalDetail(Long appraisalId) {
        Appraisal appraisal = appRepo.findById(appraisalId).get();
        if (appraisal.getAppResultId() != null) return appMapper.toDtoCustom(appraisal, appResultRepo.findById(appraisal.getAppResultId()).orElse(null));
        else return appMapper.toDtoCustom(appraisal, null);
    }

    public Page<AppraisalDto> getMypageAppraisal(Long id, Pageable pageable) {
        return appRepo.findByUserAccountId(id, pageable).map((item) -> {
            return appMapper.toDtoCustom(item, appResultRepo.findById(item.getAppResultId()).orElse(null));
        });
    }


    //처리되지 않은 요청을 세는 메서드
    public Long countRequest() {
        return appRepo.countByState1();
    }

    public AppraisalResult findById(Long id) {
        Optional<AppraisalResult> appraisal = appResultRepo.findById(id);
        return appraisal.orElse(null);
    }

    public Long findAppraisePriceByProductId(Long aLong) {
        return null;
    }

    public void apprisalDelete(Long appraisalRequestId) {
        appRepo.deleteById(appraisalRequestId);
    }

    public List<AppraisalDto> productFind(String query) {
//        return appResultRepo.findByQuery(query).stream()
//                .map(appMapper::toDto).limit(8).collect(Collectors.toUnmodifiableList());
        return null;
    }

    @Transactional
    public void appraiseChange(HashMap<String, String> data, Long appraisalId) {
        System.out.println(data.get("appraisalState"));
        Appraisal appraisal = appRepo.getReferenceById(appraisalId);
        appraisal.setAppState(AppraisalState.valueOf(data.get("appraisalState")));
    }

}
