package com.used.lux.service.admin;

import com.used.lux.domain.Appraisal;
import com.used.lux.domain.UserAccount;
import com.used.lux.dto.AppraisalDto;
import com.used.lux.repository.AppraisalRepository;
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

    private final AppraisalRepository appraisalRepository;

    private final UserAccountRepository userAccountRepository;

    @Transactional(readOnly = true)
    public Page<AppraisalDto> findAllList(Pageable pageable) {
        return appraisalRepository.findAll(pageable).map(AppraisalDto::from);
    }

    @Transactional
    public void create(AppraisalDto dto) throws Exception {
        UserAccount userAccount = userAccountRepository.getReferenceById(dto.userAccountDto().id());
        Appraisal appraisal = appraisalRepository.save(dto.toEntity(userAccount)); // 감정신청서 DB에 저장
    }


    //처리되지 않은 요청을 세는 메서드
    public Long countRequest() {
        return appraisalRepository.countByState1();
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
