package com.used.lux.service.user.auction;

import com.used.lux.domain.State;
import com.used.lux.domain.auction.Auction;
import com.used.lux.domain.constant.AuctionState;
import com.used.lux.dto.user.auction.AuctionDto;
import com.used.lux.mapper.AuctionMapper;
import com.used.lux.repository.auction.AuctionRepository;
import com.used.lux.repository.product.ProductRepository;
import com.used.lux.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final AuctionMapper auctionMapper;

    private final ProductRepository productRepository;



    private final StateRepository stateRepository;

    @Transactional(readOnly = true)
    public Page<AuctionDto> auctionListFind(Pageable pageable) {
        return auctionRepository.findByAuctionStartDate(pageable).map(auctionMapper::toDto);
    }


    @Transactional(readOnly = true)
    public List<AuctionDto> productFind(String query) {
        return auctionRepository.findByQuery(query, PageRequest.of(0, 10)).stream()
                .map(auctionMapper::toDto).limit(8).toList();
    }

    @Transactional(readOnly = true)
    public  List<AuctionDto> searchcate(Long mcategoryId, String productColor,String brandName,String productGender,String productSize,String productGrade ,String maxPrice,String minPrice,String query){

        return auctionRepository.searchAuctionBy(mcategoryId,productColor,brandName,productGender,productSize,productGrade,Integer.parseInt(maxPrice),Integer.parseInt(minPrice),query).stream().map(auctionMapper::toDto).collect(Collectors.toList());
    }

    public AuctionDto auctionFind(Long id) {
        Auction auction = auctionRepository.getReferenceById(id);
        auction.setAucViewCnt(auction.getAucViewCnt()+1);
        return auctionMapper.toDto(auctionRepository.save(auction));
    }

    /*public Integer auctionUpdate(Long auctionId, AuctionDto auctionDto, String userEmail) {
        try {
            Auction auction = auctionRepository.getReferenceById(auctionId);

            if (auction != null) {
                if (auctionDto.presentPrice() != 0) auction.setPresentPrice(auctionDto.presentPrice());
                auction.setBidder(userEmail);
                auction.setBiddingCount(auctionDto.biddingCount()+1);
                auctionRepository.save(auction);
                return 1; // 경매 입찰 성공
            }
        } catch (EntityNotFoundException e) {
            log.warn("경매 입찰 실패. 경매 입찰을 위한 정보를 찾을 수 없습니다 - {}", e.getLocalizedMessage());
        }
        return -1; // 경매 입찰 실패
    }*/

    //관리자 대쉬보드에 쓰이는 5가지 메서드
    //1.종료임박
    public Auction auctionFindByCloseNearDate() {
       return auctionRepository.findByClosingNearDate();
    }

    //2.기간내에 종료된 경매중 가장 높은 가격
    public Auction findByPrice(String bannerDateType) {
        LocalDate nowDate = LocalDate.now();
        LocalDate sectionStartDate  = LocalDate.now();

        if(bannerDateType.equals("month"))
        {
            sectionStartDate =  sectionStartDate.minusDays(31);

        }else if(bannerDateType.equals("year"))
        {
            sectionStartDate =  sectionStartDate.minusYears(1);

        }

        return auctionRepository.findByhighPriceWithState10(sectionStartDate.toString());
    }

    //3.가장 최근에 유찰된 것
    public Auction findByNearDateFailBid() {
        return auctionRepository.findByDateWithFailBid();
    }
    //4.현재 진행되는 경매중 가장 높은 가격
    public Auction findByPriceWithState9() {
        return auctionRepository.findByHighPriceWithState9();
    }

    //5.현재 진행되는 경매중 가장 많은 조회 수
    public Auction findByMostBiddingWithState9() {
        return auctionRepository.findByMostBiddingWithState9();
    }


    public Long sumProfitByDate(String bannerDateType) {

        LocalDate sectionStartDate  = LocalDate.now();

        if(bannerDateType.equals("month")) {sectionStartDate =  sectionStartDate.minusDays(31);}
        else if(bannerDateType.equals("week")) {sectionStartDate =  sectionStartDate.minusDays(7);}

        return auctionRepository.sumProfitByDate(sectionStartDate.toString());
    }




    public  void  presentTimer(Long auctionId, Long stateId){
        Auction auction = auctionRepository.getReferenceById(auctionId);
        auction.setAucState(AuctionState.valueOf("stateId"));
        auctionRepository.save(auction);
    }
    public  void  afterTimer(Long auctionId, Long stateId){
        Auction auction = auctionRepository.getReferenceById(auctionId);
        auction.setAucState(AuctionState.valueOf("stateId"));
        auction.setEndPrice(auction.getPresentPrice());
        auctionRepository.save(auction);
    }

    public List<AuctionDto> findByState10AndRecent4List() {
        return auctionRepository.findByState10AndRecent4List().stream()
                .map(auctionMapper::toDto).toList();
    }
    
}
