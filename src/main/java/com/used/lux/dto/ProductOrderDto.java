package com.used.lux.dto;

import com.used.lux.domain.ProductOrder;

public record ProductOrderDto(
        Long id,
        String name,
        String phoneNumber,
        String address,
        String email,
        String requestedTerm,
        Long invoiceNumber,
        String paymentMethod,
        int payment
) {

    public static ProductOrderDto of(Long id,
                                  String name,
                                  String phoneNumber,
                                  String address,
                                  String email,
                                  String requestedTerm,
                                  Long invoiceNumber,
                                  String paymentMethod,
                                  int payment){
        return new ProductOrderDto(id,
                name,
                phoneNumber,
                address,
                email,
                requestedTerm,
                invoiceNumber,
                paymentMethod, payment);
    }

    public static ProductOrderDto from(ProductOrderDto entity){
        return  new ProductOrderDto(
                entity.id(),
                entity.name(),
                entity.phoneNumber(),
                entity.address(),
                entity.email(),
                entity.requestedTerm(),
                entity.invoiceNumber(),
                entity.paymentMethod(),
                entity.payment()
        );
    }



}
