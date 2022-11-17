package com.used.lux.service.user.auction;

import com.used.lux.dto.user.auction.AuctionLogDto;
import com.used.lux.repository.auction.AuctionLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuctionLogService {

    private final AuctionLogRepository auctionLogRepository;

    public List<AuctionLogDto> auctionLogList(Long auctionId) {
        return auctionLogRepository.findByAuctionId(auctionId).stream()
                .map(AuctionLogDto::from).collect(Collectors.toUnmodifiableList());
    }


    public  Page<AuctionLogDto> searchAuctionLog(String bidder,Pageable pageable){
        return auctionLogRepository.findByBidder(bidder,pageable).map(AuctionLogDto::from);
    }
}
