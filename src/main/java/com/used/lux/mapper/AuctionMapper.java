package com.used.lux.mapper;

import com.used.lux.domain.auction.Auction;
import com.used.lux.dto.user.auction.AuctionDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuctionMapper {

    Auction toEntity(AuctionDto auctionDto);

    AuctionDto toDto(Auction auction);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Auction partialUpdate(AuctionDto auctionDto, @MappingTarget Auction auction);
}
