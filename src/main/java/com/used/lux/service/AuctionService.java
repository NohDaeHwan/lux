package com.used.lux.service;

import com.used.lux.dto.AuctionDto;
import com.used.lux.repository.AuctionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;

@RequiredArgsConstructor
@Transactional
@Service
public class AuctionService {

    private final AuctionRepository auctionRepository;

    public Page<AuctionDto> auctionListFind(Pageable pageable) {
        return auctionRepository.findList(pageable).map(AuctionDto::from);
    }

    public AuctionDto auctionFind(Long id) {
        return null;
    }

    public Page<AuctionDto> auctionResultFind(Pageable pageable) {
        return null;
    }

    public AuctionDto resultFind(Long id) {
        return null;
    }
}
