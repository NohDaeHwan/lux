package com.used.lux.service;

import com.used.lux.dto.ProductDto;
import com.used.lux.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdProductService {

    private final ProductRepository productRepository;

    public Page<ProductDto> getProductList(Pageable pageable) {
        return productRepository.findAll(pageable).map(ProductDto::from);
    }
}
