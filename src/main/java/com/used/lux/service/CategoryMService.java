package com.used.lux.service;

import com.used.lux.dto.CategoryMDto;
import com.used.lux.repository.CategoryMRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryMService {
    //Category_M DB의 서비스 클래스입니다. 해당 DB에 연결하려면 repository와 service 영역에 추가적인 작성이 필요합니다.
    private final CategoryMRepository categoryMRepository;

    public List<CategoryMDto> getCategoryList() {
        return categoryMRepository.findAll()
                .stream().map(CategoryMDto::from).collect(Collectors.toCollection(ArrayList::new));
    }

    public  CategoryMDto getMcategoryid(Long id){
        return CategoryMDto.from(categoryMRepository.findById(id).get());
    }
}