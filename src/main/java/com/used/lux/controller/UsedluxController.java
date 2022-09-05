package com.used.lux.controller;

import com.used.lux.response.ProductResponse;
import com.used.lux.response.ProductsResponse;
import com.used.lux.service.UsedluxService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public String usedLuxury(@RequestParam(required = false, defaultValue = "") String brandName,
                             @RequestParam(required = false, defaultValue = "") String bigCategory,
                             @RequestParam(required = false, defaultValue = "") String smallCategory,
                             @RequestParam(required = false, defaultValue = "") String gender,
                             @RequestParam(required = false, defaultValue = "") String state,
                             @RequestParam(required = false, defaultValue = "") String size,
                             @RequestParam(required = false, defaultValue = "") String productName, ModelMap map,
                             @PageableDefault(size = 30, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ProductsResponse> products = usedluxService.productFind(brandName, bigCategory, smallCategory,
                gender, state, size, productName, pageable).map(ProductsResponse::from);
        map.addAttribute("products", products);
        return "used_luxury/used_luxury"; // 뷰 아직 구현 안됨
    }

    @GetMapping("/used_luxury/{productId}")
    public String usedLuxury(@PathVariable Long productId, ModelMap model) {
        ProductResponse product = ProductResponse.from(usedluxService.productFind(productId));
        model.addAttribute("product", product);
        return "used_luxury/used_luxury_detail"; // 뷰 아직 구현 안됨
    }

    @GetMapping("/order/used_luxury/{productId}")
    public String usedLuxuryOrder(@PathVariable Long productId, ModelMap model) {
        ProductResponse product = ProductResponse.from(usedluxService.productFind(productId));
        model.addAttribute("product", product);
        return "used_luxury/used_luxury_order"; // 뷰 아직 구현 안됨
    }

}
