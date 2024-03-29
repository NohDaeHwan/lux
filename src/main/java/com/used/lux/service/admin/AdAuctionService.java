package com.used.lux.service.admin;

import com.used.lux.domain.auction.Auction;
import com.used.lux.domain.constant.AuctionState;
import com.used.lux.dto.user.auction.AuctionDto;
import com.used.lux.dto.user.auction.AuctionLogDto;
import com.used.lux.dto.admin.AdAuctionDto;
import com.used.lux.mapper.AuctionLogMapper;
import com.used.lux.mapper.AuctionMapper;
import com.used.lux.mapper.ProductMapper;
import com.used.lux.repository.auction.AuctionLogRepository;
import com.used.lux.repository.auction.AuctionRepository;
import com.used.lux.repository.useraccount.UserAccountRepository;
import com.used.lux.request.auction.AuctionUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdAuctionService {

    private final AuctionRepository auctionRepository;
    private final AuctionMapper auctionMapper;

    private final AuctionLogRepository auctionLogRepository;
    private final AuctionLogMapper auctionLogMapper;
    private final UserAccountRepository userAccountRepository;

    private final ProductMapper prodMapper;

    // Admin 경매 리스트 조회(+검색)
    @Transactional(readOnly = true)
    public Page<AuctionDto> getAuctionList(String auctionState, String auctionDate, String query, Pageable pageable) {
        return auctionRepository.findByBackAucList(auctionState, auctionDate, query, pageable).map(auctionMapper::toDto);
    }

    // Admin 경매 상세 조회(+경매로그)
    @Transactional(readOnly = true)
    public AdAuctionDto getAuctionDetail(Long auctionId) {
        Auction auction = auctionRepository.findById(auctionId).get();
        // 경매 상세
        AuctionDto auc = auctionMapper.toDtoCustom(
                auction,
                prodMapper.toDtoCustom(auction.getProd()),
                userAccountRepository.findById(auction.getUserId()).get()

        );
        // 경매 로그
        List<AuctionLogDto> aucLogList = auctionLogRepository.findByAucId(auctionId).stream().map((item) -> {
            return auctionLogMapper.toDtoCustom(item, userAccountRepository.findById(item.getUserId()).get());
        }).toList();
        return AdAuctionDto.of(auc, aucLogList);
    }

    // 업데이트
    public void auctionUpdate(Long auctionId, AuctionUpdateRequest auctionUpdateRequest){
        //업데이트에 필요한 entity 가져오기
        auctionRepository.findByStartPrice(auctionUpdateRequest.startPrice());

        //시작시간 포맷
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String startDate = auctionUpdateRequest.auctionStartDate();
        startDate = startDate.replaceAll("T", " ");
        LocalDateTime startDateTime = LocalDateTime.parse(startDate, formatter);

        //종료시간 포맷
        String closingDate = auctionUpdateRequest.auctionClosingDate();
        closingDate = closingDate.replaceAll("T", " ");
        LocalDateTime closeDateTime = LocalDateTime.parse(closingDate, formatter);

        Auction auction =auctionRepository.getReferenceById(auctionId);
        
        //수정사항 업데이트
        auction.setStartPrice(auctionUpdateRequest.startPrice());
        auction.setAucStartDate(startDateTime);
        auction.setAucEndDate(closeDateTime);
        auction.setAucState(AuctionState.WAITING);
        
        //레포지토리 저장
        auctionRepository.save(auction);

    }

}
