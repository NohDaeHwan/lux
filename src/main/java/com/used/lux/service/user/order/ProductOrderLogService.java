package com.used.lux.service.user.order;

import com.used.lux.repository.order.ProductOrderLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
        
        return productOrderLogRepository.countPriceByCreatedAt(sectionStartDate.toString());
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
        return productOrderLogRepository.countOrderByCreatedAt(sectionStartDate.toString());
    }

    public Long countOrderByState() {
        return productOrderLogRepository.countOrderByState11();
    }


    public String findByState(String bannerDateType) {
        LocalDate nowDate = LocalDate.now();
        LocalDate sectionStartDate  = LocalDate.now();

        if(bannerDateType.equals("month"))
        {
            sectionStartDate =  sectionStartDate.minusDays(31);

        }else if(bannerDateType.equals("year"))
        {
            sectionStartDate =  sectionStartDate.minusYears(1);

        }
        return productOrderLogRepository.findBySellTypeOfState(sectionStartDate.toString());
    }

    public String findByCategoryB(String bannerDateType) {
        LocalDate nowDate = LocalDate.now();
        LocalDate sectionStartDate  = LocalDate.now();

        if(bannerDateType.equals("month"))
        {
            sectionStartDate =  sectionStartDate.minusDays(31);

        }else if(bannerDateType.equals("year"))
        {
            sectionStartDate =  sectionStartDate.minusYears(1);

        }

        return productOrderLogRepository.findBySellTypeOfCategoryB(sectionStartDate.toString());
    }

    public String findByCategoryM(String bannerDateType) {
        LocalDate nowDate = LocalDate.now();
        LocalDate sectionStartDate  = LocalDate.now();

        if(bannerDateType.equals("month"))
        {
            sectionStartDate =  sectionStartDate.minusDays(31);

        }else if(bannerDateType.equals("year"))
        {
            sectionStartDate =  sectionStartDate.minusYears(1);

        }

        return productOrderLogRepository.findBySellTypeOfCategoryM(sectionStartDate.toString());
    }

    public String findByPriceRange(String bannerDateType) {
        LocalDate nowDate = LocalDate.now();
        LocalDate sectionStartDate  = LocalDate.now();

        if(bannerDateType.equals("month"))
        {
            sectionStartDate =  sectionStartDate.minusDays(31);

        }else if(bannerDateType.equals("year"))
        {
            sectionStartDate =  sectionStartDate.minusYears(1);
        }

        return  productOrderLogRepository.findByPriceRange(sectionStartDate.toString());
    }

    public String findByViewCount(String bannerDateType) {
        LocalDate nowDate = LocalDate.now();
        LocalDate sectionStartDate  = LocalDate.now();

        if(bannerDateType.equals("month"))
        {
            sectionStartDate =  sectionStartDate.minusDays(31);

        }else if(bannerDateType.equals("year"))
        {
            sectionStartDate =  sectionStartDate.minusYears(1);
        }

        return productOrderLogRepository.findByMostViewCount(sectionStartDate.toString());
    }

    public Long sumProfitByDate(String bannerDateType) {
        LocalDate sectionStartDate  = LocalDate.now();

        if(bannerDateType.equals("month")) {sectionStartDate =  sectionStartDate.minusDays(31);}
        else if(bannerDateType.equals("week")) {sectionStartDate =  sectionStartDate.minusDays(7);}

        return productOrderLogRepository.sumProfitByDate(sectionStartDate.toString());
    }

    public List<Long> profitIdByDate(String bannerDateType) {
        LocalDate sectionStartDate  = LocalDate.now();

        if(bannerDateType.equals("month")) {sectionStartDate =  sectionStartDate.minusDays(31);}
        else if(bannerDateType.equals("week")) {sectionStartDate =  sectionStartDate.minusDays(7);}

        return productOrderLogRepository.profitIdByDate(sectionStartDate.toString());
    }

    public Long countSellingProduct() {
        return productOrderLogRepository.countSellingProduct();
    }

    public Long countProgressAuction() {
        return productOrderLogRepository.countProgressAuction();
    }
}
