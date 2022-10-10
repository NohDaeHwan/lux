package com.used.lux.service;


import com.used.lux.domain.ProductOrder;
import com.used.lux.repository.ProductOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ProductOrderService {



    private final ProductOrderRepository productOrderRepository;

    public Page<ProductOrder> productListAll(Long id, Pageable pageable){
        Page<ProductOrder> productorder = productOrderRepository.findByProductId(id, pageable);
        return  productorder;
    }

    public Page<ProductOrder> orderlistPage(Long id,Pageable pageable){
        Page<ProductOrder> paging =productOrderRepository.findByProductId(id, pageable);
        return paging;
    }


}
