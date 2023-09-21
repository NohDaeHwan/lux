package com.used.lux.dto;

import java.util.List;

public record CategoryBDto(
        Long id,
        String cateBNm,
        List<CategoryMDto> cateMList
) {
}
