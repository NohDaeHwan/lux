package com.used.lux.controller.user;

import com.used.lux.dto.security.Principal;
import com.used.lux.response.SearchResponse;
import com.used.lux.response.product.ProductResponse;
import com.used.lux.response.product.ProductsResponse;
import com.used.lux.service.*;
import com.used.lux.service.admin.AdProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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


    private final AdProductService adProductService;

    private final BrandService brandService;

    private final CategoryBService categoryBService;

    private final CategoryMService categoryMService;

    private final PaginationService paginationService;

    private final SearchService searchService;

    @GetMapping
    public String productList( @AuthenticationPrincipal Principal principal,
                               @PageableDefault(size = 30) Pageable pageable,
                               @RequestParam(defaultValue = "") String productSellType,
                               @RequestParam(defaultValue = "") String productBrand,
                               @RequestParam(defaultValue = "") String productGender,
                               @RequestParam(defaultValue = "") String productSize,
                               @RequestParam(defaultValue = "") String productGrade,
                               @RequestParam(defaultValue = "") String productState,
                               @RequestParam(defaultValue = "2000-01-01") String productDate,
                               @RequestParam(defaultValue = "") String query,
                               ModelMap mm) {

        Page<ProductResponse> productResponses = adProductService.getProductList(productSellType,
                productBrand, productGender, productSize, productGrade, productState,
                productDate, query, pageable).map(ProductResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), productResponses.getTotalPages());
        SearchResponse searchResponse = searchService.getSearchList();


        mm.addAttribute("paginationBarNumbers", barNumbers);
        mm.addAttribute("productResponses", productResponses);
        mm.addAttribute("productSearchResponse", searchResponse);



        return "/front/product"; // 중고 명품 리스트 페이지
    }

    @GetMapping("/{productId}")
    public String productDetail(@PathVariable Long productId) {
        return "/front/product-detail"; // 중고 명품 리스트 페이지
    }




    /*private final UsedluxService usedluxService;

    @GetMapping
    public ResponseEntity<Page<ProductsResponse>> usedLuxury(@RequestParam(required = false, defaultValue = "") String brandName,
                                                             @RequestParam(required = false, defaultValue = "") String bigCategory,
                                                       @RequestParam(required = false, defaultValue = "") String smallCategory,
                                                       @RequestParam(required = false, defaultValue = "") String gender,
                                                       @RequestParam(required = false, defaultValue = "") String state,
                                                       @RequestParam(required = false, defaultValue = "") String size,
                                                       @RequestParam(required = false, defaultValue = "") String productName,
                                                       @PageableDefault(size = 30, sort = "created_at", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ProductsResponse> products = usedluxService.productFind(brandName, bigCategory, smallCategory,
                gender, state, size, productName, pageable).map(ProductsResponse::from);
        return ResponseEntity.status(HttpStatus.OK).body(products); // 중고 명품 리스트 페이지
    }

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
