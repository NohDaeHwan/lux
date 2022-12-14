package com.used.lux.service.user.useraccount;

import com.used.lux.dto.TotalPointDto;
import com.used.lux.dto.user.useraccount.UserAccountLogDto;
import com.used.lux.repository.useraccount.UserAccountLogRepository;
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

    public Long getTotalPoint(String userEmail) {
        TotalPointDto totalPointDto = userAccountLogRepository.getTotalPoint(userEmail);
        if (totalPointDto.point() == null) {
            return 0L;
        }
        return totalPointDto.point();
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
        return userAccountLogRepository.countOrderByCreatedAt(sectionStartDate.toString());
    }
}
