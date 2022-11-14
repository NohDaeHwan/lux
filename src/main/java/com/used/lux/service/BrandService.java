package com.used.lux.service;

import com.used.lux.domain.Brand;
import com.used.lux.dto.BrandDto;
import com.used.lux.repository.BrandRepository;
import com.used.lux.request.BrandCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class BrandService {
    //Brand DB의 서비스 클래스입니다. 해당 DB에 연결하려면 repository와 service 영역에 추가적인 작성이 필요합니다.
    private final BrandRepository brandRepository;


    @Transactional

    public BrandDto createBrand(BrandCreateRequest brandCreateRequest) {
        return BrandDto.from(brandRepository.save(Brand.of(brandCreateRequest.brandName())));
    }

    @Transactional
    public void deleteBrand(Long brandId) {
        brandRepository.deleteById(brandId);
    }

    @Transactional(readOnly = true)
    public List<BrandDto> brandList() {
        return brandRepository.findAll().stream()
                .map(BrandDto::from).collect(Collectors.toUnmodifiableList());
    }
}
