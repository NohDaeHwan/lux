package com.used.lux.service.user.product;

import com.used.lux.repository.product.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ImageService {
    //Image DB의 서비스 클래스입니다. 해당 DB에 연결하려면 repository와 service 영역에 추가적인 작성이 필요합니다.
    private final ImageRepository imageRepository;
}
