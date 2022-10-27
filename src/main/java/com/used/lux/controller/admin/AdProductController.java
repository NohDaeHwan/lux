package com.used.lux.controller.admin;

import com.used.lux.dto.BrandDto;
import com.used.lux.dto.CategoryBDto;
import com.used.lux.dto.CategoryMDto;
import com.used.lux.dto.admin.AdProductDto;
import com.used.lux.dto.security.Principal;
import com.used.lux.request.BrandCreateRequest;
import com.used.lux.request.CategoryCreateRequest;
import com.used.lux.request.ProductCreateRequest;
import com.used.lux.request.ProductUpdateRequest;
import com.used.lux.response.product.ProductResponse;
import com.used.lux.response.SearchResponse;
import com.used.lux.service.*;
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

    private final PaginationService paginationService;

    private final SearchService searchService;

    // 상품 리스트
    @GetMapping
    public String productList(@AuthenticationPrincipal Principal principal,
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
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }

        Page<ProductResponse> productResponses = adProductService.getProductList(productSellType,
                productBrand, productGender, productSize, productGrade, productState,
                productDate, query, pageable).map(ProductResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), productResponses.getTotalPages());
        SearchResponse searchResponse = searchService.getSearchList();

        mm.addAttribute("paginationBarNumbers", barNumbers);
        mm.addAttribute("productResponses", productResponses);
        mm.addAttribute("productSearchResponse", searchResponse);
        return "/admin/product";
    }

    // 상품 상세정보
    @GetMapping("/product-detail/{productId}")
    public String productDetail(@PathVariable Long productId,
                             @AuthenticationPrincipal Principal principal,
                             ModelMap mm){
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }

        AdProductDto productDetail = adProductService.getProductDetail(productId);
        mm.addAttribute("productDetail", productDetail);

        if (productDetail.productDto().productState().getStateStep().equals("신규")) {
            List<BrandDto> brandDto = adProductService.getBrandList();
            List<CategoryBDto> categoryBDtos = adProductService.getCategoryList();
            List<CategoryMDto> categoryMDtos = categoryMService.getCategoryList();

            mm.addAttribute("brandDto", brandDto);
            mm.addAttribute("categoryBDtos", categoryBDtos);
            mm.addAttribute("categoryMDtos", categoryMDtos);
            return "/admin/product-create-form";
        } else {
            return "/admin/product-detail";
        }
    }

    // 상품 상세정보
    @PostMapping("/product-detail/new")
    public String productDetailNew(@AuthenticationPrincipal Principal principal,
                                   ProductCreateRequest productCreateRequest,
                                   ModelMap mm) throws Exception {
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }

        System.out.println(productCreateRequest);
        adProductService.productCreate(productCreateRequest);
        return "redirect:/admin/product/product-detail/"+productCreateRequest.productId();
    }

    // 상품 상세정보 수정 페이지
    @GetMapping("/product-detail-update/{productId}")
    public String productDetailForm(@PathVariable Long productId,
                                @AuthenticationPrincipal Principal principal,
                                ModelMap mm){
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }

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
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }

        System.out.println(productUpdateRequest);
        adProductService.productUpdate(productId,productUpdateRequest);
        return "redirect:/admin/product/product-detail/"+productId;
    }

    // 상품 브랜드
    @GetMapping("/brand")
    public String productBrand(@AuthenticationPrincipal Principal principal,
                             ModelMap mm){
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }

        List<BrandDto> brandList = adProductService.getBrandList();
        mm.addAttribute("brandList", brandList);
        return "/admin/brand";
    }

    // 상품 브랜드 추가 페이지
    @GetMapping("/brand/new")
    public String productBrandCreate(@AuthenticationPrincipal Principal principal)
    {
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }

        return "/admin/brand-create-form";
    }

    // 상품 브랜드 추가
    @PostMapping("/brand/new/create")
    public String productBrandCreate(@AuthenticationPrincipal Principal principal,
                                     BrandCreateRequest brandCreateRequest,
                                     ModelMap mm){
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }

        BrandDto brandDto = brandService.createBrand(brandCreateRequest);
        mm.addAttribute("brandDto", brandDto);
        return "redirect:/admin/product/brand";
    }

    // 상품 브랜드 삭제
    @GetMapping("/brand/{brandId}/delete")
    public String productBrandCreate(@PathVariable Long brandId,
                                     @AuthenticationPrincipal Principal principal){
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }

        brandService.deleteBrand(brandId);
        return "redirect:/admin/product/brand";
    }

    // 상품 카테고리
    @GetMapping("/category")
    public String productCategory(@AuthenticationPrincipal Principal principal,
                               ModelMap mm){
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }

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
