package com.used.lux.service;

import com.used.lux.domain.Brand;
import com.used.lux.dto.BrandDto;
import com.used.lux.mapper.BrandMapper;
import com.used.lux.repository.BrandRepository;
import com.used.lux.request.BrandCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class BrandService {
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Transactional
    public BrandDto createBrand(BrandCreateRequest brandCreateRequest) {
        return brandMapper.toDto(brandRepository.save(Brand.builder()
                .brandName(brandCreateRequest.brandName())
                .build()));
    }

    @Transactional
    public void deleteBrand(Long brandId) {
        brandRepository.deleteById(brandId);
    }

    @Transactional(readOnly = true)
    public List<BrandDto> brandList() {
        return brandMapper.toDtoList(brandRepository.findAll());
    }
}
