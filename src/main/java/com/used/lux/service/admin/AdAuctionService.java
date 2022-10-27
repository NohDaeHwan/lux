package com.used.lux.service.admin;

import com.used.lux.domain.Auction;
import com.used.lux.domain.State;
import com.used.lux.dto.AuctionDto;
import com.used.lux.dto.AuctionLogDto;
import com.used.lux.dto.admin.AdAuctionDto;
import com.used.lux.repository.*;
import com.used.lux.request.AuctionUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdAuctionService {

    private final AuctionRepository auctionRepository;

    private final AuctionLogRepository auctionLogRepository;

    private final StateRepository stateRepository;


    public Page<AuctionDto> getAuctionList(String auctionState, String auctionDate, String query, Pageable pageable) {
        return auctionRepository.searchAuction(auctionState, auctionDate, query, pageable).map(AuctionDto::from);
    }

    public AdAuctionDto getAuctionDetail(Long auctionId) {
        // 경매 상세
        AuctionDto auctionDto = AuctionDto.from(auctionRepository.findById(auctionId).get());
        // 경매 로그
        List<AuctionLogDto> auctionLogDtos = auctionLogRepository.findByProductId(auctionDto.productDto().id())
                .stream().map(AuctionLogDto::from).collect(Collectors.toCollection(ArrayList::new));
        return AdAuctionDto.of(auctionDto, auctionLogDtos);
    }

    // 업데이트
    public void auctionUpdate(Long auctionId, AuctionUpdateRequest auctionUpdateRequest){
        //업데이트에 필요한 entity 가져오기
        auctionRepository.findByStartPrice(auctionUpdateRequest.startPrice());
        State state=stateRepository.findByStateStep("경매중");

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
        auction.setAuctionStartDate(startDateTime);
        auction.setAuctionClosingDate(closeDateTime);
        auction.setState(state);
        
        //레포지토리 저장
        auctionRepository.save(auction);

    }

}
