package com.used.lux.dto;

import com.used.lux.domain.CategoryM;

public record CategoryMDto(
        Long id,
        Long categoryBId,
        String categoryMName
) {
    public static CategoryMDto of(Long id, Long categoryBId, String categoryMName) {
        return new CategoryMDto(id, categoryBId, categoryMName);
    }

    public static CategoryMDto from(CategoryM entity) {
        return new CategoryMDto(
                entity.getId(),
                entity.getCategoryB().getId(),
                entity.getCategoryMName()
        );
    }

}
