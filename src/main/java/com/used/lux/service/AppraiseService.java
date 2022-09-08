package com.used.lux.service;

import com.used.lux.component.FileHandler;
import com.used.lux.domain.AppraisalImage;
import com.used.lux.domain.ForAppraisal;
import com.used.lux.domain.UserAccount;
import com.used.lux.dto.ForAppraisalDto;
import com.used.lux.dto.ForAppraisalsDto;
import com.used.lux.repository.AppraiseImageRepository;
import com.used.lux.repository.AppraiseRepository;
import com.used.lux.repository.UserAccountRepository;
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

    private final AppraiseRepository appraiseRepository;

    private final UserAccountRepository userAccountRepository;

    private final AppraiseImageRepository appraiseImageRepository;

    private final FileHandler fileHandler;

    @Transactional(readOnly = true)
    public Page<ForAppraisalsDto> findAllList(Pageable pageable) {
        return appraiseRepository.findAll(pageable).map(ForAppraisalsDto::from);
    }

    @Transactional
    public void create(ForAppraisalDto dto) throws Exception {
        UserAccount userAccount = userAccountRepository.getReferenceById(dto.userAccountDto().id());
        ForAppraisal forAppraisal = appraiseRepository.save(dto.toEntity(userAccount)); // 감정신청서 DB에 저장

        List<AppraisalImage> imgeList = fileHandler.parseFileInfo(dto, userAccount, forAppraisal); // 이미지 처리

        // 파일이 존재할 때에만 처리
        if(!imgeList.isEmpty()) {
            for(AppraisalImage appraisalImage : imgeList) {
                // 파일을 DB에 저장
                System.out.println(appraisalImage);
                appraiseImageRepository.save(appraisalImage); // 이미지 이름 및 경로 DB에 저장
            }
        }
    }

}
