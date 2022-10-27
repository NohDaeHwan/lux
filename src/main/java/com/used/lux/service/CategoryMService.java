package com.used.lux.service;

import com.used.lux.domain.CategoryM;
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
    private CategoryM categoryM =null;
    public List<CategoryMDto> getCategoryList() {
        return categoryMRepository.findAll()
                .stream().map(CategoryMDto::from).collect(Collectors.toCollection(ArrayList::new));
    }

    public boolean middleCategoryCreate(String st)
    {
        boolean output = false;
        //같은 이름이 있는지 확인
        if(!categoryMRepository.existsByCategoryMName(st))
        {
            //없을시 추가
            categoryM.setCategoryMName(st);
            categoryMRepository.save(categoryM);
            output = true;

        }

        return output;
    }
    public void middleCategoryDelete(String st){
        //M 카테고리 삭제(단일)
        categoryMRepository.deleteByCategoryMName(st);

    }
    public void middelCategoryDeleteByBCategoryId(Long categoryId)
    {
        //B카테고리에 종속된 M카테고리 삭제
        categoryMRepository.deleteAllByCategoryB_Id(categoryId);

    }

    public void middleCategoryExsist(String st){
        //해당 카테고리가 존재하는지 확인
        categoryMRepository.existsByCategoryMName(st);
    }

    public List<String> middlecategoryExsistByBCategory(Long categoryId)
    {
        //B카테고리에 종속된 M카테고리 출력
        List<String> list = categoryMRepository.findAllById(categoryId);
        return list;
    }
}