package com.used.lux.categoryCAD;

import com.used.lux.domain.CategoryB;
import com.used.lux.domain.CategoryM;
import com.used.lux.service.Category_BService;

import com.used.lux.service.Category_MService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class bigCategoryCAD {
    private final Category_BService category_bService;
    private final Category_MService category_mService;
    //bigcategory 생성 메소드
    public void bigCategoryCreate(String st)
    {
        category_bService.bigCategoryCreate(st);
    }

    //bigcategory 삭제 메소드 1차 ::하위카테고리 삭제여부
    public List<String> checkExsistMCategoryByMCategory(String st)
    {
        //B카테고리에 종속되어 있는 M카테고리를 보여준다.
        List<String> list = category_mService.middlecategoryExsistByBCategory(st);
        return list;
    }

    //bigcategory 삭제 메소드 2차 :: 하위카테고리가 없거나 확인하고 이를 요청한 경우 실행한다
    public void bigCategoryDelete(String st)
    {
        //B카테고리에 종속된 M카테고리 먼저 제거한다
        category_mService.middelCategoryDeleteByBCategory(st);
        //B카테고리 제거
        category_bService.bigCategoryDelete(st);
    }


}
