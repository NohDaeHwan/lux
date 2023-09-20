package com.used.lux.mapper;

import com.used.lux.domain.Brand;
import com.used.lux.dto.BrandDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BrandMapper {
    Brand toEntity(BrandDto brandDto);

    BrandDto toDto(Brand brand);

    List<BrandDto> toDtoList(List<Brand> brandList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Brand partialUpdate(BrandDto brandDto, @MappingTarget Brand brand);
}