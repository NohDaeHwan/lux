package com.used.lux.repository;

import com.used.lux.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StateRepository extends JpaRepository<State, Long> {

    State findByStateStep(String stateStep);
}
