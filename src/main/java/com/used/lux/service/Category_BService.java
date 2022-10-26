package com.used.lux.service;


import com.used.lux.domain.CategoryB;
import com.used.lux.repository.Category_BRepository;
import com.used.lux.repository.Category_MRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Category_BService {
    //Category_B DB의 서비스 클래스입니다. 해당 DB에 연결하려면 repository와 service 영역에 추가적인 작성이 필요합니다.
    private final Category_BRepository category_BRepository;

    public void bigCategoryCreate(String st)
    {
        CategoryB CB = new CategoryB();
        CB.setCategoryBName(st);
        category_BRepository.save(CB);
    }
    public void bigCategoryDelete( String st){
        category_BRepository.deleteByCategoryBName(st);
    }
    public void bigCategoryExist( String st){
        category_BRepository.existsByCategoryBName(st);
    }
}
