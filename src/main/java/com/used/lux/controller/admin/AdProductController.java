package com.used.lux.controller.admin;

import com.used.lux.dto.BrandDto;
import com.used.lux.dto.CategoryBDto;
import com.used.lux.dto.CategoryMDto;
import com.used.lux.dto.admin.AdProductDto;
import com.used.lux.dto.security.Principal;
import com.used.lux.request.BrandCreateRequest;
import com.used.lux.request.CategoryCreateRequest;
import com.used.lux.request.ProductUpdateRequest;

import com.used.lux.response.product.ProductResponse;
import com.used.lux.service.BrandService;
import com.used.lux.service.CategoryBService;
import com.used.lux.service.CategoryMService;
import com.used.lux.service.admin.AdProductService;
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
@RequestMapping("/admin/product")
@Controller
public class AdProductController {

    private final AdProductService adProductService;

    private final BrandService brandService;

    private final CategoryBService categoryBService;

    private final CategoryMService categoryMService;

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

    @GetMapping("/product-detail/{productId}")
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


    // 상품 상세정보 수정 페이지
    @GetMapping("/product-detail-update/{productId}")
    public String productDetailForm(@PathVariable Long productId,
                                @AuthenticationPrincipal Principal principal,
                                ModelMap mm){
        /*if (principal == null) {
            return "redirect:/login";
        }
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }*/
        System.out.println("called function");
        AdProductDto productDetail = adProductService.getProductDetail(productId);
        List<BrandDto> brandDto = adProductService.getBrandList();
        List<CategoryBDto> categoryBDtos = adProductService.getCategoryList();
        List<CategoryMDto> categoryMDtos = categoryMService.getCategoryList();

        mm.addAttribute("productDetail", productDetail);
        mm.addAttribute("brandDto", brandDto);
        mm.addAttribute("categoryBDtos", categoryBDtos);
        mm.addAttribute("categoryMDtos", categoryMDtos);
        return "/admin/product-detail-update";
    }

  
    // 상품 상세정보 업데이트
    @PostMapping("/product-detail-update/{productId}/update")
    public String productDetailUpdate(@PathVariable Long productId,
                                      @AuthenticationPrincipal Principal principal,
                                      ProductUpdateRequest productUpdateRequest){
        /*if (principal == null) {
            return "redirect:/login";
        }
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }*/
        System.out.println(productUpdateRequest);
        adProductService.productUpdate(productId,productUpdateRequest);

        return "redirect:/admin/product/product-detail/"+productId;

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

    // 상품 브랜드 추가 페이지
    @GetMapping("/brand/new")
    public String productBrandCreate(@AuthenticationPrincipal Principal principal)
    {
        return "/admin/brand-create-form";
    }

    // 상품 브랜드 추가
    @PostMapping("/brand/new/create")
    public String productBrandCreate(@AuthenticationPrincipal Principal principal,
                                     BrandCreateRequest brandCreateRequest,
                                     ModelMap mm){
        BrandDto brandDto = brandService.createBrand(brandCreateRequest);
        mm.addAttribute("brandDto", brandDto);
        return "redirect:/admin/product/brand";
    }

    // 상품 브랜드 삭제
    @GetMapping("/brand/{brandId}/delete")
    public String productBrandCreate(@PathVariable Long brandId,
                                     @AuthenticationPrincipal Principal principal){
        brandService.deleteBrand(brandId);
        return "redirect:/admin/product/brand";
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
        List<CategoryBDto> categoryList = adProductService.getCategoryList();
        mm.addAttribute("categoryList", categoryList);
        return "/admin/category";
    }

    // 상품 카테고리 추가
    @ResponseBody
    @PostMapping("/category/new/create")
    public ResponseEntity<CategoryBDto> productBrandCreate(@AuthenticationPrincipal Principal principal,
                                             CategoryCreateRequest categoryCreateRequest){
        CategoryBDto categoryBDto = categoryBService.createCategory(categoryCreateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(categoryBDto);
    }

}
