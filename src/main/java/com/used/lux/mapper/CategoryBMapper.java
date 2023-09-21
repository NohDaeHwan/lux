package com.used.lux.mapper;

import com.used.lux.domain.CategoryB;
import com.used.lux.dto.CategoryBDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryBMapper {
    CategoryB toEntity(CategoryBDto categoryBDto);

    CategoryBDto toDto(CategoryB categoryB);

    List<CategoryBDto> toDtoList(List<CategoryB> categoryBList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CategoryB partialUpdate(CategoryBDto categoryBDto, @MappingTarget CategoryB categoryB);
}