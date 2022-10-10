package com.used.lux.dto;

import com.used.lux.domain.Product;
import com.used.lux.domain.ProductOrder;
import com.used.lux.domain.UserAccount;

import java.time.LocalDateTime;

public record ProductOrderDto(
        Long id,
        String name,
        String phoneNumber,
        String address,
        String email,
        String requestedTerm,
        ProductDto productDto,
        UserAccountDto userAccountDto,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static ProductOrderDto of(Long id, String name, String phoneNumber, String address, String email,
                           String requestedTerm, ProductDto productDto, UserAccountDto userAccountDto,
                           LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new ProductOrderDto(id, name, phoneNumber, address, email, requestedTerm, productDto,
                userAccountDto, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static ProductOrderDto from(ProductOrder entity){
        return  new ProductOrderDto(
                entity.getId(),
                entity.getName(),
                entity.getPhoneNumber(),
                entity.getAddress(),
                entity.getEmail(),
                entity.getRequestedTerm(),
                ProductDto.from(entity.getProduct()),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public ProductOrder toEntity(Product product, UserAccount userAccount) {
        return ProductOrder.of(name, phoneNumber, address, email, requestedTerm, product, userAccount);
    }

}
