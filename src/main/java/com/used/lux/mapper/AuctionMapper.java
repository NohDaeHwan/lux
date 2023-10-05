package com.used.lux.mapper;

import com.used.lux.domain.auction.Auction;
import com.used.lux.domain.useraccount.UserAccount;
import com.used.lux.dto.user.auction.AuctionDto;
import com.used.lux.dto.user.product.ProductDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuctionMapper {

    @Mapping(target = "prod.prodBrand", source = "prod.prodBrand.brandName")
    AuctionDto toDto(Auction auction);

    default AuctionDto toDtoCustom(Auction auction, ProductDto prod, UserAccount userAccount) {
        return AuctionDto.builder()
                .id(auction.getId())
                .aucState(auction.getAucState().name())
                .startPrice(auction.getStartPrice())
                .presentPrice(auction.getPresentPrice())
                .endPrice(auction.getEndPrice())
                .aucStartDate(auction.getAucStartDate())
                .aucEndDate(auction.getAucEndDate())
                .biddingCnt(auction.getBiddingCnt())
                .userId(auction.getUserId())
                .bidder(userAccount != null ? userAccount.getUserName() : null)
                .prod(prod)
                .createdAt(auction.getCreatedAt())
                .createdBy(auction.getCreatedBy())
                .modifiedAt(auction.getModifiedAt())
                .modifiedBy(auction.getModifiedBy())
                .build();
    }
}
