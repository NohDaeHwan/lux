package com.used.lux.service.user.useraccount;

import com.used.lux.dto.TotalPointDto;
import com.used.lux.dto.user.useraccount.UserAccountLogDto;
import com.used.lux.mapper.UserAccountLogMapper;
import com.used.lux.repository.useraccount.UserAccountLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class UserAccountLogService {

    private final UserAccountLogMapper userAccountLogMapper;

    private final UserAccountLogRepository userAccountLogRepository;
    public Page<UserAccountLogDto> getPointList(Long userId, Pageable pageable) {
        return userAccountLogRepository.findByUserIdFront(userId,pageable).map(userAccountLogMapper::toDto);
    }

    public Long getTotalPoint(Long userId) {
        TotalPointDto totalPointDto = userAccountLogRepository.getTotalPoint(userId);
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
