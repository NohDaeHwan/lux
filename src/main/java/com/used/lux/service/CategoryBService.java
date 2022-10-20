package com.used.lux.service;

import com.used.lux.domain.CategoryB;
import com.used.lux.dto.CategoryBDto;
import com.used.lux.repository.CategoryBRepository;
import com.used.lux.request.CategoryCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryBService {
    //Category_B DB의 서비스 클래스입니다. 해당 DB에 연결하려면 repository와 service 영역에 추가적인 작성이 필요합니다.
    private final CategoryBRepository categoryBRepository;

    public CategoryBDto createCategory(CategoryCreateRequest categoryCreateRequest) {
        return CategoryBDto.from(categoryBRepository.save(CategoryB.of(categoryCreateRequest.categoryName())));
    }
}
