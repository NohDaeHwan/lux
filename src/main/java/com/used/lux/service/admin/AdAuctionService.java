package com.used.lux.service.admin;

import com.used.lux.dto.AuctionDto;
import com.used.lux.dto.AuctionLogDto;
import com.used.lux.dto.admin.AdAuctionDto;
import com.used.lux.repository.AuctionLogRepository;
import com.used.lux.repository.AuctionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdAuctionService {

    private final AuctionRepository auctionRepository;

    private final AuctionLogRepository auctionLogRepository;

    public Page<AuctionDto> getAuctionList(Pageable pageable) {
        return auctionRepository.findAll(pageable).map(AuctionDto::from);
    }

    public AdAuctionDto getAuctionDetail(Long auctionId) {
        // 경매 상세
        AuctionDto auctionDto = AuctionDto.from(auctionRepository.findById(auctionId).get());
        // 경매 로그
        List<AuctionLogDto> auctionLogDtos = auctionLogRepository.findByProductId(auctionDto.productDto().id())
                .stream().map(AuctionLogDto::from).collect(Collectors.toCollection(ArrayList::new));
        return AdAuctionDto.of(auctionDto, auctionLogDtos);
    }
}
