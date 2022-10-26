package com.used.lux.categoryCAD;

import com.used.lux.service.Category_MService;

public class smallCategoryCAD {
    private Category_MService category_mService;

    public void middleCategoryCreate(String st){
        //같은 이름이 있는지 확인
        category_mService.middleCategoryCreate(st);
    }
}
