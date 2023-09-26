package com.used.lux.mapper;

import com.used.lux.domain.product.ProductLog;
import com.used.lux.dto.user.product.ProductLogDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductLogMapper {
    ProductLog toEntity(ProductLogDto productLogDto);

    ProductLogDto toDto(ProductLog productLog);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductLog partialUpdate(ProductLogDto productLogDto, @MappingTarget ProductLog productLog);
}