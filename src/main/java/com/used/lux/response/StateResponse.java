package com.used.lux.response;

import com.used.lux.dto.StateDto;

public record StateResponse(
        Long id,
        String stateName,
        String stateStep
) {

    public static StateResponse of(Long id, String stateName, String stateStep) {
        return new StateResponse(id, stateName, stateStep);
    }

    public static StateResponse from(StateDto dto) {
        return new StateResponse(
                dto.id(),
                dto.stateName(),
                dto.stateStep()
        );
    }

}
