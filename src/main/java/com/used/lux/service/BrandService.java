package com.used.lux.service;

import com.used.lux.domain.Brand;
import com.used.lux.dto.BrandDto;
import com.used.lux.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BrandService {
    //Brand DB의 서비스 클래스입니다. 해당 DB에 연결하려면 repository와 service 영역에 추가적인 작성이 필요합니다.
    private final BrandRepository brandRepository;


    public void brandCreate(String st)
    {
        Long id = null;

        Brand brand = new Brand();
        brand.setBrandName(st);

        brandRepository.save(brand);
    }

    public void brandDelete(String a) {
        Long brandId = null;
        Brand brand = new Brand();
        brand.setBrandName(a);
        brandRepository.delete(brand);
    }
}
