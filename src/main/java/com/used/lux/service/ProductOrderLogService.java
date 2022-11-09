package com.used.lux.service;

import com.used.lux.repository.ProductOrderLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ProductOrderLogService {
    //ProductOrderLog DB의 서비스 클래스입니다. 해당 DB에 연결하려면 repository와 service 영역에 추가적인 작성이 필요합니다.
    private final ProductOrderLogRepository productOrderLogRepository;

    //판매수익 매소드화
    public Long countPriceByDate(String bannerDateType) {
        LocalDate nowDate = LocalDate.now();
        LocalDate sectionStartDate  = LocalDate.now();
        
        if(bannerDateType.equals("month"))
        {
            sectionStartDate =  sectionStartDate.minusDays(31);
            
        }else if(bannerDateType.equals("year"))
        {
            sectionStartDate =  sectionStartDate.minusYears(1);
            
        }
        
        return productOrderLogRepository.countPriceByCreatedAt(sectionStartDate.toString(),nowDate.toString());
    }

    public Long countOrderByDate(String bannerDateType) {
        LocalDate nowDate = LocalDate.now();
        LocalDate sectionStartDate  = LocalDate.now();

        if(bannerDateType.equals("month"))
        {
            sectionStartDate =  sectionStartDate.minusDays(31);

        }else if(bannerDateType.equals("year"))
        {
            sectionStartDate =  sectionStartDate.minusYears(1);

        }
        return productOrderLogRepository.countOrderByCreatedAt(sectionStartDate.toString(),nowDate.toString());
    }

    public Long countorderByState() {
        return productOrderLogRepository.countorderByState11();
    }
}
