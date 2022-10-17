package com.used.lux.categoryCAD;

import com.used.lux.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;


public class brandCAD{

    @Autowired
    private BrandService brandService;

    public void brandCreate(String a) {
        brandService.brandCreate(a);
    }
    public void brandDelete(String a)
    {
        brandService.brandDelete(a);
    }

}
