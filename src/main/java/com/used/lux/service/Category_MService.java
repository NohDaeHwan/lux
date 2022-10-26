package com.used.lux.service;

import com.used.lux.domain.CategoryM;
import com.used.lux.repository.Category_MRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Category_MService {
    //Category_M DB의 서비스 클래스입니다. 해당 DB에 연결하려면 repository와 service 영역에 추가적인 작성이 필요합니다.
    private final Category_MRepository  category_mRepository;
    private CategoryM categoryM =null;

    public boolean middleCategoryCreate(String st)
    {
        boolean output = false;
        //같은 이름이 있는지 확인
        if(!category_mRepository.existsByCategoryMName(st))
        {
            //없을시 추가
            categoryM.setCategoryMName(st);
            category_mRepository.save(categoryM);
            output = true;

        }

        return output;
    }
    public void middleCategoryDelete(String st){
        //M 카테고리 삭제(단일)
        category_mRepository.deleteByCategoryMName(st);

    }
    public void middelCategoryDeleteByBCategory(String st)
    {
        //B카테고리에 종속된 M카테고리 삭제
        category_mRepository.deleteAllByCategoryB(st);

    }

    public void middleCategoryExsist(String st){
        //해당 카테고리가 존재하는지 확인
        category_mRepository.existsByCategoryMName(st);
    }

    public List<String> middlecategoryExsistByBCategory(String st)
    {
        //B카테고리에 종속된 M카테고리 출력
        List<String> list = category_mRepository.findAllByCategoryMName(st);
        return list;
    }

}
