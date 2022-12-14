package com.used.lux.service;

import com.used.lux.domain.CategoryM;
import com.used.lux.dto.CategoryMDto;
import com.used.lux.repository.CategoryMRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryMService {
    //Category_M DB의 서비스 클래스입니다. 해당 DB에 연결하려면 repository와 service 영역에 추가적인 작성이 필요합니다.
    private final CategoryMRepository categoryMRepository;
    private final CategoryBService categoryBService;

    public List<CategoryMDto> getMiddleCategoryList() {
        return categoryMRepository.findAll()
                .stream().map(CategoryMDto::from).collect(Collectors.toCollection(ArrayList::new));
    }

    public  CategoryMDto getMcategoryid(Long id){
        return CategoryMDto.from(categoryMRepository.findById(id).get());
    }

    public boolean middleCategoryCreate(String st,Long id)
    {
        CategoryM categoryM = new CategoryM();
        boolean output = false;
        //같은 이름이 있는지 확인
        if(!categoryMRepository.existsByCategoryMName(st))
        {
            //없을시 추가
            categoryM.setCategoryMName(st);
            categoryM.setCategoryB(categoryBService.findById(id));
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

    public boolean middleCategoryExsist(String st){
        //해당 카테고리가 존재하는지 확인
        return categoryMRepository.existsByCategoryMName(st);

    }

    public List<String> middlecategoryExsistByBCategory(Long categoryId)
    {
        //B카테고리에 종속된 M카테고리 출력
        List<String> list = categoryMRepository.findAllByCategoryB_Id(categoryId);
        return list;
    }

    public void middleCategoryDeleteById(Long categoryId) {
        categoryMRepository.deleteById(categoryId);
    }

    public CategoryM findById(Long id) {
        Optional<CategoryM> categoryMOptional = categoryMRepository.findById(id);
        return categoryMOptional.orElse(null);
    }
}