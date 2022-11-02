package com.used.lux.service;

import com.used.lux.domain.CategoryB;
import com.used.lux.dto.CategoryBDto;
import com.used.lux.repository.CategoryBRepository;
import com.used.lux.request.CategoryCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryBService {
    //Category_B DB의 서비스 클래스입니다. 해당 DB에 연결하려면 repository와 service 영역에 추가적인 작성이 필요합니다.
    private final CategoryBRepository categoryBRepository;


    //B카테고리 추가 BDto반환
    public CategoryBDto createCategory(CategoryCreateRequest categoryCreateRequest) {
        return CategoryBDto.from(categoryBRepository.save(CategoryB.of(categoryCreateRequest.categoryName())));
    }

    //B카테고리 추가 void
    public void bigCategoryCreate(String st)
    {
        CategoryB CB = CategoryB.of(null,st);
        categoryBRepository.save(CB);
    }

    //B 카테고리 모두 출력하기
    public List<CategoryBDto> getBigCategoryAll(){return categoryBRepository.findAll()
            .stream().map(CategoryBDto::from).collect(Collectors.toCollection(ArrayList::new));}




    public boolean bigCategoryExist( String st){
        return categoryBRepository.existsByCategoryBName(st);
    }

    public void bigCategoryDelete(Long categoryId) {categoryBRepository.deleteById(categoryId);}

    public CategoryB findById(Long id){
        Optional<CategoryB> categoryB = categoryBRepository.findById(id);
        return categoryB.orElse(null);
    }
}
