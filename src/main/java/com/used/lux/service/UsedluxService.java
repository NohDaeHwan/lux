package com.used.lux.service;

import com.used.lux.domain.Product;
import com.used.lux.dto.ProductDto;
import com.used.lux.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UsedluxService {

    private final ProductRepository productRepository;

    public Page<ProductDto> productFind(String brandName, String bigCategory, String smallCategory, String gender,
                                        String state, String size, String productName, Pageable pageable) {
        return productRepository.findByQuery(brandName, bigCategory,
                smallCategory, gender, state, size, productName, pageable).map(ProductDto::from);
    }

    public ProductDto productFind(Long productId) {
        Product product = productRepository.getReferenceById(productId);
        product.setProductViewCount(product.getProductViewCount()+1);
        return ProductDto.from(productRepository.save(product));
    }

}
