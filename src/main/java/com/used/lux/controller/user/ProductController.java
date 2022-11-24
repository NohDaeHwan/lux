package com.used.lux.controller.user;

import com.used.lux.dto.user.product.ProductDto;
import com.used.lux.dto.UserGradeDto;
import com.used.lux.dto.security.Principal;
import com.used.lux.request.order.OrderCreateRequest;

import com.used.lux.dto.BrandDto;
import com.used.lux.dto.CategoryBDto;
import com.used.lux.response.product.ProductResponse;
import com.used.lux.service.*;
import com.used.lux.service.user.order.ProductOrderService;
import com.used.lux.service.user.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/product")
@Controller
public class ProductController {

    private final BrandService brandService;

    private final CategoryBService categoryBService;

    private final PaginationService paginationService;

    private final ProductService productService;

    private final ProductOrderService productOrderService;

    private final UserGradeService userGradeService;


    @GetMapping
    public String productList(@RequestParam(defaultValue = "") String productColor,
                              @RequestParam(defaultValue = "") String productBrand,
                              @RequestParam(defaultValue = "") String productGender,
                              @RequestParam(defaultValue = "") String productSize,
                              @RequestParam(defaultValue = "") String productGrade,
                              @RequestParam(defaultValue = "10000000") String maxPrice,
                              @RequestParam(defaultValue = "1000") String minPrice,
                              @RequestParam(defaultValue = "") String query,
                              @PageableDefault(size = 30) Pageable pageable,
                              ModelMap mm) {

        Page<ProductResponse> products = productService.frontProductFind(productColor, productBrand, productGender,
                productSize, productGrade, maxPrice, minPrice, query, pageable).map(ProductResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), products.getTotalPages());

        List<CategoryBDto> categoryList = categoryBService.categoryList();
        List<BrandDto> brandList = brandService.brandList();

        System.out.println(barNumbers);
        mm.addAttribute("paginationBarNumbers", barNumbers);
        mm.addAttribute("products", products);
        mm.addAttribute("categoryList", categoryList);
        mm.addAttribute("brandList", brandList);


        System.out.println("function"+products);
        return "/front/product"; // 중고 명품 리스트 페이지
    }

    @GetMapping("/{productId}")
    public String productDetail(@PathVariable Long productId, ModelMap mm, @AuthenticationPrincipal  Principal principal) {
        if (principal != null) {
            mm.addAttribute("principal",principal);
        }
        List<CategoryBDto> categoryList = categoryBService.categoryList();
        List<UserGradeDto> gradeList = userGradeService.getGradeList();
        ProductDto productDto = productService.productDetail(productId);

        mm.addAttribute("categoryList", categoryList);
        mm.addAttribute("gradeList", gradeList);
        mm.addAttribute("productDto", productDto);


        return "/front/product-detail"; // 중고 명품 리스트 페이지
    }
    @GetMapping("/{productId}/order")
    public  String productOrder(@PathVariable Long productId, ModelMap mm, @AuthenticationPrincipal  Principal principal){
        if (principal != null) {
            mm.addAttribute("principal",principal);
        }
        List<CategoryBDto> categoryList = categoryBService.categoryList();
        List<UserGradeDto> gradeList = userGradeService.getGradeList();
        ProductDto productDto = productService.productDetail(productId);

        mm.addAttribute("categoryList", categoryList);
        mm.addAttribute("gradeList", gradeList);
        mm.addAttribute("productDto", productDto);

        return "front/product-order";
    }

    @PostMapping("/{productId}/order/loading")
    public String productOrder(@AuthenticationPrincipal Principal principal,
                               @PathVariable Long productId,
                               OrderCreateRequest orderCreateRequest){
        productOrderService.createOrder(principal,productId,orderCreateRequest);

        return "redirect:/product/success";
    }
    @GetMapping("/success")
    public String success(ModelMap mm) {
        List<CategoryBDto> categoryList = categoryBService.categoryList();
        mm.addAttribute("categoryList", categoryList);
        return "front/success";
    }



}
