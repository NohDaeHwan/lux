package com.used.lux.service;

import com.used.lux.repository.ProductOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.DoubleStream;

@RequiredArgsConstructor
@Service
public class AdOrderService {

    private final ProductOrderRepository productOrderRepository;

    public DoubleStream getOrderList(Pageable pageable) {
        return null;
    }
}
