package com.used.lux.mapper;

import com.used.lux.domain.order.ProductOrderLog;
import com.used.lux.dto.user.order.ProductOrderLogDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductOrderLogMapper {
    ProductOrderLog toEntity(ProductOrderLogDto productOrderLogDto);

    ProductOrderLogDto toDto(ProductOrderLog productOrderLog);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductOrderLog partialUpdate(ProductOrderLogDto productOrderLogDto, @MappingTarget ProductOrderLog productOrderLog);
}