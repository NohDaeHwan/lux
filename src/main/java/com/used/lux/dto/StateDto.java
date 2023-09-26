package com.used.lux.dto;

import lombok.Builder;

@Builder
public record StateDto(
        String stateId,
        String stateName
) {
}
