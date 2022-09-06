package com.used.lux.service;

import com.used.lux.domain.Product;
import com.used.lux.dto.ProductDto;
import com.used.lux.repository.UsedluxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Transactional
@Service
public class UsedluxService {

    private final UsedluxRepository usedluxRepository;

    public Page<ProductDto> productFind(String brandName, String bigCategory, String smallCategory, String gender,
                                        String state, String size, String productName, Pageable pageable) {
        return usedluxRepository.findByQuery(brandName, bigCategory,
                smallCategory, gender, state, size, productName, pageable).map(ProductDto::from);
    }

    public ProductDto productFind(Long productId) {
        return usedluxRepository.findById(productId).map(ProductDto::from)
                .orElseThrow(() -> new EntityNotFoundException("제품이 없습니다 - productId: " + productId));
    }


}
