package com.used.lux.mapper;

import com.used.lux.domain.order.ProductOrderCancel;
import com.used.lux.domain.product.Product;
import com.used.lux.domain.useraccount.UserAccount;
import com.used.lux.dto.user.order.ProductOrderCancelDto;
import com.used.lux.dto.user.product.ImageDto;
import com.used.lux.dto.user.product.ProductDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductOrderCancelMapper {

    default ProductOrderCancelDto toDtoCustom(ProductOrderCancel prodOrderCancel, UserAccount user, Product prod) {
        return ProductOrderCancelDto.builder()
                .id(prodOrderCancel.getId())
                .userNm(user.getUserName())
                .prodNm(prod.getProdNm())
                .prodPrice(prod.getProdPrice())
                .cancelTerm(prodOrderCancel.getCancelTerm())
                .createdAt(prodOrderCancel.getCreatedAt())
                .createdBy(prodOrderCancel.getCreatedBy())
                .modifiedAt(prodOrderCancel.getModifiedAt())
                .modifiedBy(prodOrderCancel.getModifiedBy())
                .build();
    }
}