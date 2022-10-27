package com.used.lux.response;

import com.used.lux.dto.BrandDto;
import com.used.lux.dto.StateDto;

import java.util.List;

public record SearchResponse(
        List<BrandDto> brandDtos,
        List<StateDto> stateDtos
)  {

    public static SearchResponse of(List<BrandDto> brandDtos, List<StateDto> stateDtos) {
        return new SearchResponse(brandDtos, stateDtos);
    }

}
