package com.used.lux.response;


import com.used.lux.domain.ProductOrder;
import org.springframework.data.domain.Page;

public record ProductOrderResponse(
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

    public  static ProductOrderResponse of(Long id,
                                           String name,
                                           String phoneNumber,
                                           String address,
                                           String email,
                                           String requestedTerm,
                                           Long invoiceNumber,
                                           String paymentMethod,
                                           int payment){
        return new ProductOrderResponse (id,
                 name,
                 phoneNumber,
                 address,
                 email,
                 requestedTerm,
                 invoiceNumber,
                 paymentMethod,
                 payment);

    }



}
