package com.used.lux.service;

import com.used.lux.dto.BrandDto;
import com.used.lux.dto.StateDto;
import com.used.lux.mapper.BrandMapper;
import com.used.lux.repository.BrandRepository;
import com.used.lux.repository.StateRepository;
import com.used.lux.response.SearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SearchService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    private final StateRepository stateRepository;

    public SearchResponse getSearchList() {
        List<BrandDto> brandDtos = brandRepository.findAll().stream()
                .map(brandMapper::toDto).collect(Collectors.toCollection(ArrayList::new));
        List<StateDto> stateDtos = stateRepository.findAll().stream()
                .map(StateDto::from).collect(Collectors.toCollection(ArrayList::new));
        return SearchResponse.of(brandDtos, stateDtos);
    }

}
