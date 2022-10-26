package com.used.lux.service.admin;

import com.used.lux.domain.ProductOrderCancel;
import com.used.lux.domain.State;
import com.used.lux.dto.ProductOrderCancelDto;
import com.used.lux.dto.ProductOrderDto;
import com.used.lux.dto.admin.AdProductOrderDto;
import com.used.lux.repository.ProductOrderCancelRepository;
import com.used.lux.repository.ProductOrderRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdOrderService {

    private final ProductOrderRepository productOrderRepository;

    private final ProductOrderCancelRepository productOrderCancelRepository;

    public Page<ProductOrderDto> getOrderList(Pageable pageable) {
        return productOrderRepository.findAll(pageable).map(ProductOrderDto::from);
    }
    public AdProductOrderDto getOrderDetail(Long orderId) {
        ProductOrderDto productOrderDto = ProductOrderDto.from(productOrderRepository.findById(orderId).get());
        ProductOrderCancelDto productOrderCancelDto = ProductOrderCancelDto.from(productOrderCancelRepository.findByOrderCancelId(orderId));
        return AdProductOrderDto.of(productOrderDto, productOrderCancelDto);
    }
}
