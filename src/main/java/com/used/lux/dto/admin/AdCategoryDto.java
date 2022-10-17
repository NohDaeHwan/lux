package com.used.lux.dto.admin;

import com.used.lux.dto.*;

import java.util.List;

public record AdCategoryDto(
        List<CategoryBDto> categoryBDtos
) {
    public static AdCategoryDto of(List<CategoryBDto> categoryBDtos) {
        return new AdCategoryDto(categoryBDtos);
    }
}

//public record AdCategoryDto(
//        List<CategoryBDto> categoryBDtos,
//        List<BrandDto> brandDtos
//) {
//    public static AdCategoryDto of(List<CategoryBDto> categoryBDtos, List<BrandDto> brandDtos) {
//        return new AdCategoryDto(categoryBDtos, brandDtos);
//    }
//}
