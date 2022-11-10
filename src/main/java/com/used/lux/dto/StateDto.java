package com.used.lux.dto;

import com.used.lux.domain.State;

public record StateDto(
        Long id,
        String stateName,
        String stateStep
)  {
    public static StateDto of(Long id, String stateName, String stateStep) {
        return new StateDto(id, stateName, stateStep);
    }

    public static StateDto from(State entity) {
        return new StateDto(
                entity.getId(),
                entity.getStateName(),
                entity.getStateStep()
        );
    }

    public State toDto() {
        return State.of(id, stateName, stateStep);
    }

}
