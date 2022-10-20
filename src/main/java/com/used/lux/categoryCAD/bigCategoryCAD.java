package com.used.lux.categoryCAD;

import com.used.lux.domain.CategoryB;
import com.used.lux.domain.CategoryM;
import com.used.lux.service.Category_BService;
import com.used.lux.service.Category_MService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class bigCategoryCAD {
    private final Category_BService category_bService;



    @PersistenceContext
    private EntityManager em;


    //bigcategory 생성 메소드
    public void bigCategoryCreate(String st)
    {
        category_bService.bigCategoryCreate(st);
    }

    //bigcategory 삭제 메소드 주의:: 하위카테고리 삭제여부
    @Transactional
    public String  bigCategoryDelete(String st)
    {
        CategoryM categoryM = new CategoryM();
        CategoryB categoryB = new CategoryB();
        em.persist(categoryM);
        category_bService.bigCategoryDelete(st);
        return "aaaaaa";
    }


}
