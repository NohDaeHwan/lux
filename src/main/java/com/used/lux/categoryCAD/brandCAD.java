package com.used.lux.categoryCAD;

import com.used.lux.service.BrandService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class brandCAD{

    private final BrandService brandService;

    //브랜드 추가 메소드
    public String  brandCreate(String a) {
        //브랜드 추가하기 전 해당 브랜드가 있는지 검사
        if(!brandService.brandExist(a)) {brandService.brandCreate(a);return "브랜드 추가 성공";}
        else {return "브랜드가 이미 존재합니다";}
    }
    //브랜드를 삭제합니다. 브랜드이름 옆에 있는 버튼을 눌러 삭제한다고 가정
    public String brandDelete(String a)
    {
        brandService.brandDelete(a);
        System.out.println("================================================================");
        return "브랜드 삭제 성공";
    }

}
