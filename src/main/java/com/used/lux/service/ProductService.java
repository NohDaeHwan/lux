package com.used.lux.service;

import com.used.lux.dto.ProductDto;
import com.used.lux.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {
    //Product DB의 서비스 클래스입니다. 해당 DB에 연결하려면 repository와 service 영역에 추가적인 작성이 필요합니다.
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Page<ProductDto> productFind(String productColor, String productBrand, String productGender, String productSize,
                                        String productGrade, String maxPrice, String minPrice, String query, Pageable pageable) {
        return productRepository.findByQuery(productColor, productBrand,
                productGender, productSize, productGrade, maxPrice, minPrice, query, pageable).map(ProductDto::from);
    }

    @Transactional(readOnly = true)
    public List<ProductDto> productFind(String query) {
        return productRepository.findByQuery(query, PageRequest.of(0, 10)).stream()
                .map(ProductDto::from).collect(Collectors.toUnmodifiableList());
    }

}
