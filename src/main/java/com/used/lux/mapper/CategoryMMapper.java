package com.used.lux.mapper;

import com.used.lux.domain.CategoryM;
import com.used.lux.dto.CategoryMDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMMapper {
    CategoryM toEntity(CategoryMDto categoryMDto);

    CategoryMDto toDto(CategoryM categoryM);

    List<CategoryMDto> toDtoList(List<CategoryM> categoryMList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CategoryM partialUpdate(CategoryMDto categoryMDto, @MappingTarget CategoryM categoryM);
}