package com.used.lux.mapper;

import com.used.lux.domain.auction.AuctionLog;
import com.used.lux.domain.useraccount.UserAccount;
import com.used.lux.dto.user.auction.AuctionLogDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuctionLogMapper {
    AuctionLog toEntity(AuctionLogDto auctionLogDto);

    AuctionLogDto toDto(AuctionLog auctionLog);

    List<AuctionLogDto> toDtoList(List<AuctionLog> auctionLog);

    default AuctionLogDto toDtoCustom(AuctionLog auctionLog, UserAccount userAccount) {
        return AuctionLogDto.builder()
                .id(auctionLog.getId())
                .aucId(auctionLog.getAucId())
                .bidder(userAccount.getUserName())
                .userId(auctionLog.getUserId())
                .prodNm(auctionLog.getProdNm())
                .presentPrice(auctionLog.getPresentPrice())
                .createdAt(auctionLog.getCreatedAt())
                .createdBy(auctionLog.getCreatedBy())
                .modifiedAt(auctionLog.getModifiedAt())
                .modifiedBy(auctionLog.getModifiedBy())
                .build();
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AuctionLog partialUpdate(AuctionLogDto auctionLogDto, @MappingTarget AuctionLog auctionLog);
}