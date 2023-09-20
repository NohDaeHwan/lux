package com.used.lux.mapper;

import com.used.lux.domain.order.ProductOrder;
import com.used.lux.dto.user.order.ProductOrderDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductOrderMapper {
    ProductOrder toEntity(ProductOrderDto productOrderDto);

    ProductOrderDto toDto(ProductOrder productOrder);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductOrder partialUpdate(ProductOrderDto productOrderDto, @MappingTarget ProductOrder productOrder);
}