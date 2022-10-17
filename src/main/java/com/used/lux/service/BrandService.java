package com.used.lux.service;

import com.used.lux.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandService {
    //Brand DB의 서비스 클래스입니다. 해당 DB에 연결하려면 repository와 service 영역에 추가적인 작성이 필요합니다.
    private final BrandRepository brandRepository;
}
