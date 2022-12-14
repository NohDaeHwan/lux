package com.used.lux.service.user.auction;

import com.used.lux.domain.State;
import com.used.lux.domain.auction.Auction;
import com.used.lux.dto.user.auction.AuctionDto;
import com.used.lux.dto.user.auction.AuctionLogDto;
import com.used.lux.repository.auction.AuctionLogRepository;
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

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class AuctionService {

    private final AuctionRepository auctionRepository;

    private final ProductRepository productRepository;



    private final StateRepository stateRepository;

    @Transactional(readOnly = true)
    public Page<AuctionDto> auctionListFind(Pageable pageable) {
        return auctionRepository.findByAuctionStartDate(pageable).map(AuctionDto::from);
    }


    @Transactional(readOnly = true)
    public List<AuctionDto> productFind(String query) {
        return auctionRepository.findByQuery(query, PageRequest.of(0, 10)).stream()
                .map(AuctionDto::from).limit(8).collect(Collectors.toUnmodifiableList());
    }

    @Transactional(readOnly = true)
    public  List<AuctionDto> searchcate(Long mcategoryId, String productColor,String brandName,String productGender,String productSize,String productGrade ,String maxPrice,String minPrice,String query){

        return auctionRepository.searchAuctionBy(mcategoryId,productColor,brandName,productGender,productSize,productGrade,Integer.parseInt(maxPrice),Integer.parseInt(minPrice),query).stream().map(AuctionDto::from).collect(Collectors.toList());
    }

    public AuctionDto auctionFind(Long id) {
        Auction auction = auctionRepository.getReferenceById(id);
        auction.getProduct().setProductViewCount(auction.getProduct().getProductViewCount()+1);
        return AuctionDto.from(auctionRepository.save(auction));
    }

    /*public Integer auctionUpdate(Long auctionId, AuctionDto auctionDto, String userEmail) {
        try {
            Auction auction = auctionRepository.getReferenceById(auctionId);

            if (auction != null) {
                if (auctionDto.presentPrice() != 0) auction.setPresentPrice(auctionDto.presentPrice());
                auction.setBidder(userEmail);
                auction.setBiddingCount(auctionDto.biddingCount()+1);
                auctionRepository.save(auction);
                return 1; // ?????? ?????? ??????
            }
        } catch (EntityNotFoundException e) {
            log.warn("?????? ?????? ??????. ?????? ????????? ?????? ????????? ?????? ??? ???????????? - {}", e.getLocalizedMessage());
        }
        return -1; // ?????? ?????? ??????
    }*/

    //????????? ??????????????? ????????? 5?????? ?????????
    //1.????????????
    public Auction auctionFindByCloseNearDate() {
       return auctionRepository.findByClosingNearDate();
    }

    //2.???????????? ????????? ????????? ?????? ?????? ??????
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

    //3.?????? ????????? ????????? ???
    public Auction findByNearDateFailBid() {
        return auctionRepository.findByDateWithFailBid();
    }
    //4.?????? ???????????? ????????? ?????? ?????? ??????
    public Auction findByPriceWithState9() {
        return auctionRepository.findByHighPriceWithState9();
    }

    //5.?????? ???????????? ????????? ?????? ?????? ?????? ???
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
        State state = stateRepository.findById(stateId).get();
        auction.setState(state);
        auctionRepository.save(auction);
    }
    public  void  afterTimer(Long auctionId, Long stateId){
        Auction auction = auctionRepository.getReferenceById(auctionId);
        State state = stateRepository.findById(stateId).get();
        auction.setState(state);
        auction.setClosingPrice(auction.getPresentPrice());
        auctionRepository.save(auction);
    }


    public List<AuctionDto> findByState10AndRecent4List() {
        return auctionRepository.findByState10AndRecent4List().stream()
                .map(AuctionDto::from).collect(Collectors.toUnmodifiableList());
    }
    
}
