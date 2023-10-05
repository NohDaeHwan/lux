package com.used.lux.service.user.auction;

import com.used.lux.dto.user.auction.AuctionLogDto;
import com.used.lux.dto.user.auction.AuctionMypageLogDto;
import com.used.lux.mapper.AuctionLogMapper;
import com.used.lux.repository.auction.AuctionLogRepository;
import com.used.lux.repository.useraccount.UserAccountRepository;
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
    private final AuctionLogMapper auctionLogMapper;

    private final UserAccountRepository userRepo;

    public List<AuctionLogDto> auctionLogList(Long auctionId) {
        return auctionLogRepository.findByAucId(auctionId).stream().map((item) -> {
            return auctionLogMapper.toDtoCustom(item, userRepo.findById(item.getUserId()).get());
        }).toList();
    }

    public Page<AuctionMypageLogDto> searchAuctionLog(Long userId, Pageable pageable){
        return auctionLogRepository.findByUserId(userId, pageable);
    }
}
