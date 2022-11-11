package com.used.lux.dto;

import com.used.lux.domain.CategoryB;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record CategoryBDto(
        Long id,
        String categoryBName,
        Set<CategoryMDto> categoryMDtos
) {
    public static CategoryBDto of(Long id, String categoryBName, Set<CategoryMDto> categoryMDtos) {
        return new CategoryBDto(id, categoryBName, categoryMDtos);
    }

    public static CategoryBDto from(CategoryB entity) {
        return new CategoryBDto(
                entity.getId(),
                entity.getCategoryBName(),
                entity.getCategoryMs().stream()
                        .map(CategoryMDto::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new))


        );
    }

}
