package com.used.lux.controller.admin;

import com.used.lux.dto.BrandDto;
import com.used.lux.dto.admin.AdCategoryDto;
import com.used.lux.dto.admin.AdProductDto;
import com.used.lux.dto.security.Principal;
import com.used.lux.request.BrandCreateRequest;
import com.used.lux.response.product.ProductResponse;
import com.used.lux.service.BrandService;
import com.used.lux.service.admin.AdProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/admin/product")
@Controller
public class AdProductController {

    private final AdProductService adProductService;

    private final BrandService brandService;

    // 상품 리스트
    @GetMapping
    public String productList(@AuthenticationPrincipal Principal principal,
                           @PageableDefault(size = 30) Pageable pageable,
                           ModelMap mm) {
//        if (principal == null) {
//            return "redirect:/login";
//        }
//        if (principal.role().getName() != "ROLE_ADMIN") {
//            return "redirect:/";
//        }
        Page<ProductResponse> productResponses = adProductService.getProductList(pageable).map(ProductResponse::from);
        mm.addAttribute("productResponses", productResponses);
        return "/admin/product";
    }

    // 상품 등록하기(상품 상세정보 부재시)
    @GetMapping("/new")
    public String productCreate(ModelMap mm) {
        mm.addAttribute("hello","대시보드");
        return "/admin/product-create-form";
    }

    // 상품 상세정보
    @GetMapping("/{productId}")
    public String productDetail(@PathVariable Long productId,
                             @AuthenticationPrincipal Principal principal,
                             ModelMap mm){
        /*if (principal == null) {
            return "redirect:/login";
        }
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }*/
        AdProductDto productDetail = adProductService.getProductDetail(productId);
        mm.addAttribute("productDetail", productDetail);
        return "/admin/product-detail";
    }

    // 상품 브랜드
    @GetMapping("/brand")
    public String productBrand(@AuthenticationPrincipal Principal principal,
                             ModelMap mm){
        /*if (principal == null) {
            return "redirect:/login";
        }
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }*/
        List<BrandDto> brandList = adProductService.getBrandList();
        mm.addAttribute("brandList", brandList);
        return "/admin/brand";
    }

    // 상품 브랜드 추가
    @GetMapping("/brand/new")
    public String productBrandCreate(@AuthenticationPrincipal Principal principal,
                                     BrandCreateRequest brandCreateRequest,
                                     ModelMap mm){
        BrandDto brandDto = brandService.createBrand(brandCreateRequest);
        mm.addAttribute("brandDto", brandDto);
        return "/admin/brand-create-form";
    }

    // 상품 카테고리
    @GetMapping("/category")
    public String productCategory(@AuthenticationPrincipal Principal principal,
                               ModelMap mm){
        /*if (principal == null) {
            return "redirect:/login";
        }
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }*/
        System.out.printf("test");
        AdCategoryDto categoryList = adProductService.getCategoryList();
        mm.addAttribute("categoryList", categoryList);
        return "/admin/category";
    }
}
