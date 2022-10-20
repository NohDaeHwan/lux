package com.used.lux.service;

import com.used.lux.domain.State;
import com.used.lux.dto.ProductOrderDto;
import com.used.lux.dto.StateDto;
import com.used.lux.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StateService {
    //State DB의 서비스 클래스입니다. 해당 DB에 연결하려면 repository와 service 영역에 추가적인 작성이 필요합니다.
    private final StateRepository stateRepository;

    public List<State> getStateList() {
        return stateRepository.findAll();
    }


}
