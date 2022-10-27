package com.used.lux.service;

import com.used.lux.domain.CategoryB;
import com.used.lux.dto.CategoryBDto;
import com.used.lux.repository.CategoryBRepository;
import com.used.lux.request.CategoryCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryBService {
    //Category_B DB의 서비스 클래스입니다. 해당 DB에 연결하려면 repository와 service 영역에 추가적인 작성이 필요합니다.
    private final CategoryBRepository categoryBRepository;


    //B카테고리 추가 BDto반환
    public CategoryBDto createCategory(CategoryCreateRequest categoryCreateRequest) {
        //System.out.println(categoryCreateRequest.categoryName());
        //System.out.println(CategoryB.of(categoryCreateRequest.categoryName()).getCategoryMs());
        //System.out.println(CategoryB.of(categoryCreateRequest.categoryName()).getCategoryBName());

        return CategoryBDto.from(categoryBRepository.save(CategoryB.of(categoryCreateRequest.categoryName())));
    }

    //B카테고리 추가 void
    public void bigCategoryCreate(String st)
    {
        CategoryB CB = CategoryB.of(null,st);
        categoryBRepository.save(CB);
    }

    //B 카테고리 모두 출력하기
    public List<CategoryB> getBigCategoryAll(){return categoryBRepository.findAll();}




    public boolean bigCategoryExist( String st){
        return categoryBRepository.existsByCategoryBName(st);
    }

    public void bigCategoryDelete(Long categoryId) {categoryBRepository.deleteById(categoryId);}
}
