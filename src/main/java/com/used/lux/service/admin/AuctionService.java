package com.used.lux.service.admin;

import com.used.lux.dto.AuctionDto;
import com.used.lux.dto.ProductDto;
import com.used.lux.repository.AuctionRepository;
import com.used.lux.repository.ProductRepository;
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

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class AuctionService {

    private final AuctionRepository auctionRepository;

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Page<AuctionDto> auctionListFind(String auctionColor, String auctionBrand, String auctionGender, String auctionSize,
                                            String auctionGrade, String maxPrice, String minPrice, String query, Pageable pageable) {
        return auctionRepository.findByQuery(auctionColor, auctionBrand, auctionGender,
                auctionSize, auctionGrade, maxPrice, minPrice, query, pageable).map(AuctionDto::from);
    }

    @Transactional(readOnly = true)
    public List<AuctionDto> productFind(String query) {
        return auctionRepository.findByQuery(query, PageRequest.of(0, 10)).stream()
                .map(AuctionDto::from).collect(Collectors.toUnmodifiableList());
    }

    /*public AuctionDto auctionFind(Long id) {
        Auction auction = auctionRepository.getReferenceById(id);
        auction.getProduct().setProductViewCount(auction.getProduct().getProductViewCount()+1);
        productRepository.save(auction.getProduct());
        return AuctionDto.from(auctionRepository.save(auction));
    }

    public Page<AuctionDto> auctionResultFind(Pageable pageable) {
        return auctionRepository.findResult(pageable).map(AuctionDto::from);
    }

    public AuctionDto resultFind(Long id) {
        return auctionRepository.findById(id).map(AuctionDto::from).get();
    }

    public Integer auctionUpdate(Long auctionId, AuctionDto auctionDto, String userEmail) {
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

}
