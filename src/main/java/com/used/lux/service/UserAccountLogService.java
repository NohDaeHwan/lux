package com.used.lux.service;

import com.used.lux.dto.UserAccountLogDto;
import com.used.lux.repository.UserAccountLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class UserAccountLogService {
    //UserAccountLog DB의 서비스 클래스입니다. 해당 DB에 연결하려면 repository와 service 영역에 추가적인 작성이 필요합니다.
    private final UserAccountLogRepository userAccountLogRepository;
    public Page<UserAccountLogDto> getPointList(String userEmail, Pageable pageable) {
        return userAccountLogRepository.findByUserEmailFront(userEmail,pageable).map(UserAccountLogDto::from);
    }

    public Long countCustomerByDate(String bannerDateType) {
        LocalDate nowDate = LocalDate.now();
        LocalDate sectionStartDate  = LocalDate.now();

        if(bannerDateType.equals("month"))
        {
            sectionStartDate =  sectionStartDate.minusDays(31);

        }else if(bannerDateType.equals("year"))
        {
            sectionStartDate =  sectionStartDate.minusYears(1);

        }
        return userAccountLogRepository.countOrderByCreatedAt(sectionStartDate.toString(),nowDate.toString());
    }
}
