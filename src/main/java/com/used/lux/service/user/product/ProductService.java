package com.used.lux.service.user.product;

import com.used.lux.domain.constant.AppraisalGrade;
import com.used.lux.domain.constant.ProductState;
import com.used.lux.domain.product.Product;
import com.used.lux.dto.user.product.ProductDto;
import com.used.lux.mapper.ProductMapper;
import com.used.lux.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    //Product DB의 서비스 클래스입니다. 해당 DB에 연결하려면 repository와 service 영역에 추가적인 작성이 필요합니다.
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional(readOnly = true)
    public Page<ProductDto> frontProductFind(String productColor, String productBrand, String productGender, String productSize,
                                        String productGrade, String maxPrice, String minPrice, String query, Pageable pageable) {
        return productRepository.findByFrontProductList(productColor, productBrand,
                productGender, productSize, productGrade, Integer.parseInt(maxPrice), Integer.parseInt(minPrice), query, pageable).map(productMapper::toDtoCustom);
    }

    @Transactional(readOnly = true)
    public List<ProductDto> productFind(String query) {
        return productRepository.findByQuery(query).stream()
                .map(productMapper::toDtoCustom).limit(8).toList();
    }

    @Transactional
    public ProductDto productDetail(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        assert product != null;
        product.setProdViewCnt(product.getProdViewCnt()+1);
        productRepository.save(product);
        return productMapper.toDtoCustom(product);
    }

    //카테고리 중고 검색
    @Transactional(readOnly = true)
    public List<ProductDto> catesearch(Long mcategoryId,String productColor, String productBrand, String productGender, String productSize
                                             ,String productGrade, String maxPrice, String minPrice, String query) {
        return productRepository.findByCateQuery(mcategoryId,productBrand,productColor,productGender,productSize,productGrade,
                        Long.parseLong(maxPrice),Long.parseLong(minPrice),query).stream().map(productMapper::toDtoCustom).toList();
    }

    public List<ProductDto> findByState6AndRecent4List() {
        return productRepository.findByState6AndRecent4List().stream().map(productMapper::toDtoCustom).toList();
    }

    public List<String> gradeList() {
        List<String> result = new ArrayList<>();
        result.add(AppraisalGrade.S.name());
        result.add(AppraisalGrade.A.name());
        result.add(AppraisalGrade.B.name());

        return result;
    }

    public List<String> stateList() {
        List<String> result = new ArrayList<>();
        result.add(ProductState.WAITING.name());
        result.add(ProductState.SELL.name());
        result.add(ProductState.COMPLETE.name());
        result.add(ProductState.CANCEL.name());

        return result;
    }
}
