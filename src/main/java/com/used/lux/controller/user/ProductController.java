package com.used.lux.controller.user;

import com.used.lux.response.product.ProductsResponse;
import com.used.lux.dto.BrandDto;
import com.used.lux.dto.CategoryBDto;
import com.used.lux.service.BrandService;
import com.used.lux.service.CategoryBService;
import com.used.lux.service.PaginationService;
import com.used.lux.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/product")
@Controller
public class ProductController {

    private final BrandService brandService;

    private final CategoryBService categoryBService;

    private final PaginationService paginationService;

    private final ProductService productService;


    @GetMapping
    public String productList(@RequestParam(defaultValue = "") String productColor,
                              @RequestParam(defaultValue = "") String productBrand,
                              @RequestParam(defaultValue = "") String productGender,
                              @RequestParam(defaultValue = "") String productSize,
                              @RequestParam(defaultValue = "") String productGrade,
                              @RequestParam(defaultValue = "") String productPrice,
                              @RequestParam(defaultValue = "") String productName,
                              @PageableDefault(size = 30) Pageable pageable,
                              ModelMap mm) {
        Page<ProductsResponse> products = productService.productFind(productColor, productBrand, productGender,
                productSize, productGrade, productPrice, productName, pageable).map(ProductsResponse::from);
        List<CategoryBDto> categoryList = categoryBService.categoryList();
        List<BrandDto> brandList = brandService.brandList();
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), products.getTotalPages());

        mm.addAttribute("paginationBarNumbers", barNumbers);
        mm.addAttribute("products", products);
        mm.addAttribute("categoryList", categoryList);
        mm.addAttribute("brandList", brandList);
        return "/front/product"; // 중고 명품 리스트 페이지
    }

    @GetMapping("/{productId}")
    public String productDetail(@PathVariable Long productId) {
        return "/front/product-detail"; // 중고 명품 리스트 페이지
    }

    /*
    @GetMapping("/detail/{productId}")
    public ResponseEntity<ProductResponse> usedLuxury(@PathVariable Long productId) {
        ProductResponse product = ProductResponse.from(usedluxService.productFind(productId));
        return ResponseEntity.status(HttpStatus.OK).body(product); // 중고 명품 상세 페이지
    }

    @GetMapping("/order/{productId}")
    public ResponseEntity<ProductResponse> usedLuxuryOrder(@PathVariable Long productId) {
        ProductResponse product = ProductResponse.from(usedluxService.productFind(productId));
        return ResponseEntity.status(HttpStatus.OK).body(product); // 중고 명품 구매 페이지
    }*/

}
