package com.used.lux.controller;

import com.used.lux.response.product.ProductResponse;
import com.used.lux.response.product.ProductsResponse;
import com.used.lux.service.UsedluxService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class UsedluxController {

    private UsedluxService usedluxService;

    @GetMapping("/used_luxury")
    public ResponseEntity<Page<ProductsResponse>> usedLuxury(@RequestParam(required = false, defaultValue = "") String brandName,
                                                             @RequestParam(required = false, defaultValue = "") String bigCategory,
                                                       @RequestParam(required = false, defaultValue = "") String smallCategory,
                                                       @RequestParam(required = false, defaultValue = "") String gender,
                                                       @RequestParam(required = false, defaultValue = "") String state,
                                                       @RequestParam(required = false, defaultValue = "") String size,
                                                       @RequestParam(required = false, defaultValue = "") String productName, ModelMap map,
                                                       @PageableDefault(size = 30, sort = "created_at", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ProductsResponse> products = usedluxService.productFind(brandName, bigCategory, smallCategory,
                gender, state, size, productName, pageable).map(ProductsResponse::from);
        return ResponseEntity.status(HttpStatus.OK).body(products); // 중고 명품 리스트 페이지
    }

    @GetMapping("/used_luxury/{productId}")
    public ResponseEntity<ProductResponse> usedLuxury(@PathVariable Long productId, ModelMap model) {
        ProductResponse product = ProductResponse.from(usedluxService.productFind(productId));
        return ResponseEntity.status(HttpStatus.OK).body(product); // 중고 명품 상세 페이지
    }

    @GetMapping("/order/used_luxury/{productId}")
    public ResponseEntity<ProductResponse> usedLuxuryOrder(@PathVariable Long productId, ModelMap model) {
        ProductResponse product = ProductResponse.from(usedluxService.productFind(productId));
        return ResponseEntity.status(HttpStatus.OK).body(product); // 중고 명품 구매 페이지
    }

}
