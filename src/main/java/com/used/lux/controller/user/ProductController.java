package com.used.lux.controller.user;

import com.used.lux.dto.UserGradeDto;
import com.used.lux.dto.security.Principal;
import com.used.lux.dto.user.product.ProductDto;
import com.used.lux.request.order.OrderCreateRequest;

import com.used.lux.dto.BrandDto;
import com.used.lux.dto.CategoryBDto;
import com.used.lux.service.*;
import com.used.lux.service.user.order.ProductOrderService;
import com.used.lux.service.user.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

        Page<ProductDto> prodList = productService.frontProductFind(productColor, productBrand, productGender,
                productSize, productGrade, maxPrice, minPrice, query, pageable);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), prodList.getTotalPages());
        List<CategoryBDto> cateList = categoryBService.categoryList();
        List<BrandDto> brandList = brandService.brandList();

        mm.addAttribute("paginationBarNumbers", barNumbers);
        mm.addAttribute("prodList", prodList);
        mm.addAttribute("cateList", cateList);
        mm.addAttribute("brandList", brandList);

        return "/front/product/product-main";
    }

    @GetMapping("/{productId}")
    public String productDetail(@PathVariable Long productId, ModelMap mm, @AuthenticationPrincipal  Principal principal) {
        if (principal != null) {
            mm.addAttribute("principal",principal);
        }
        List<CategoryBDto> cateList = categoryBService.categoryList();
        List<UserGradeDto> gradeList = userGradeService.getGradeList();
        ProductDto prod = productService.productDetail(productId);

        mm.addAttribute("cateList", cateList);
        mm.addAttribute("gradeList", gradeList);
        mm.addAttribute("prod", prod);


        return "/front/product/product-detail";
    }
    @GetMapping("/{productId}/order")
    public  String productOrder(@PathVariable Long productId,
                                ModelMap mm,
                                @AuthenticationPrincipal  Principal principal) {
        if (principal != null) {
            mm.addAttribute("principal", principal);
        }
        List<CategoryBDto> cateList = categoryBService.categoryList();
        List<UserGradeDto> gradeList = userGradeService.getGradeList();
        ProductDto prod = productService.productDetail(productId);

        mm.addAttribute("cateList", cateList);
        mm.addAttribute("gradeList", gradeList);
        mm.addAttribute("prod", prod);

        return "front/product/product-order";
    }

    @ResponseBody
    @PostMapping("/order/loading")
    public ResponseEntity<?> productOrder(@AuthenticationPrincipal Principal principal,
                                       @RequestBody OrderCreateRequest orderCreateRequest){
        productOrderService.createOrder(principal, orderCreateRequest);
        return ResponseEntity.status(HttpStatus.OK).body("성공");
    }

    @GetMapping("/success")
    public String success(ModelMap mm) {
        List<CategoryBDto> cateList = categoryBService.categoryList();
        mm.addAttribute("cateList", cateList);
        return "front/success";
    }

}
