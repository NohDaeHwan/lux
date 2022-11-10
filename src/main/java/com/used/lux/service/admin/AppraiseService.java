package com.used.lux.service.admin;

import com.used.lux.domain.AppraisalRequest;
import com.used.lux.domain.UserAccount;
import com.used.lux.dto.AppraisalRequestDto;
import com.used.lux.repository.AppraisalRequestRepository;
import com.used.lux.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class AppraiseService {

    private final AppraisalRequestRepository appraisalRequestRepository;

    private final UserAccountRepository userAccountRepository;

    @Transactional(readOnly = true)
    public Page<AppraisalRequestDto> findAllList(Pageable pageable) {
        return appraisalRequestRepository.findAll(pageable).map(AppraisalRequestDto::from);
    }

    @Transactional
    public void create(AppraisalRequestDto dto) throws Exception {
        UserAccount userAccount = userAccountRepository.getReferenceById(dto.userAccount().id());
        AppraisalRequest appraisalRequest = appraisalRequestRepository.save(dto.toEntity(userAccount)); // 감정신청서 DB에 저장
    }

    public AppraisalDto appraisalDetail(Long productId) {
        return AppraisalDto.from(appraisalRepository.findById(productId).get());
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
