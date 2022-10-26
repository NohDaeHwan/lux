package com.used.lux.service;

import com.used.lux.domain.Brand;
import com.used.lux.dto.BrandDto;
import com.used.lux.repository.BrandRepository;
import com.used.lux.request.BrandCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class BrandService {
    //Brand DB의 서비스 클래스입니다. 해당 DB에 연결하려면 repository와 service 영역에 추가적인 작성이 필요합니다.
    private final BrandRepository brandRepository;

    public void brandCreate(String st)
    {
        Brand brand = new Brand();
        brand.setBrandName(st);

        brandRepository.save(brand);
    }

    public int brandDelete(String a) {
        return brandRepository.deleteByBrandName(a);
    }

    public boolean brandExist(String st)
    {
      return brandRepository.existsByBrandName(st);
    }

    public BrandDto createBrand(BrandCreateRequest brandCreateRequest) {
        return BrandDto.from(brandRepository.save(Brand.of(brandCreateRequest.brandName())));
    }

    public void deleteBrand(Long brandId) {
        brandRepository.deleteById(brandId);
    }
}
