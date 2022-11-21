package com.used.lux.service.user.product;

import com.used.lux.domain.product.Product;
import com.used.lux.dto.user.product.ProductDto;
import com.used.lux.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {
    //Product DB의 서비스 클래스입니다. 해당 DB에 연결하려면 repository와 service 영역에 추가적인 작성이 필요합니다.
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Page<ProductDto> frontProductFind(String productColor, String productBrand, String productGender, String productSize,
                                        String productGrade, String maxPrice, String minPrice, String query, Pageable pageable) {
        return productRepository.findByFrontProductList(productColor, productBrand,
                productGender, productSize, productGrade, Integer.parseInt(maxPrice), Integer.parseInt(minPrice), query, pageable).map(ProductDto::from);
    }

    @Transactional(readOnly = true)
    public List<ProductDto> productFind(String query) {
        return productRepository.findByQuery(query, PageRequest.of(0, 10)).stream()
                .map(ProductDto::from).limit(8).collect(Collectors.toUnmodifiableList());
    }


    public ProductDto productDetail(Long productId) {
        return ProductDto.from(productRepository.findById(productId).get());
    }

    //카테고리 중고 검색
    @Transactional(readOnly = true)
    public List<ProductDto> catesearch(Long mcategoryId,String productColor, String productBrand, String productGender, String productSize
                                             ,String productGrade, String maxPrice, String minPrice, String query) {
        return productRepository.searchProductBy(mcategoryId,productBrand,productColor,productGender,productSize,productGrade,Integer.parseInt(maxPrice),Integer.parseInt(minPrice),query).stream()
                .map(ProductDto::from).collect(Collectors.toUnmodifiableList());
    }

    public List<ProductDto> findByState6AndRecent4List() {
        return productRepository.findByState6AndRecent4List().stream().map(ProductDto::from).collect(Collectors.toUnmodifiableList());
    }


}
