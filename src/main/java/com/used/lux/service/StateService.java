package com.used.lux.service;

import com.used.lux.dto.StateDto;
import com.used.lux.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StateService {
    //State DB의 서비스 클래스입니다. 해당 DB에 연결하려면 repository와 service 영역에 추가적인 작성이 필요합니다.
    private final StateRepository stateRepository;

    public List<StateDto> getStateList() {
        return stateRepository.findAll().stream()
                .map(StateDto::from).collect(Collectors.toCollection(ArrayList::new));
    }


}
