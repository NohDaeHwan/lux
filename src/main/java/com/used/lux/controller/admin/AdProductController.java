package com.used.lux.controller.admin;

import com.used.lux.domain.constant.ProductState;
import com.used.lux.dto.BrandDto;
import com.used.lux.dto.CategoryBDto;
import com.used.lux.dto.CategoryMDto;
import com.used.lux.dto.admin.AdProductDto;
import com.used.lux.dto.security.Principal;
import com.used.lux.dto.user.product.ProductDto;
import com.used.lux.request.BrandCreateRequest;
import com.used.lux.request.CategoryCreateRequest;
import com.used.lux.request.product.ProductSaveRequest;
import com.used.lux.request.product.ProductUpdateRequest;
import com.used.lux.service.*;
import com.used.lux.service.admin.AdProductService;
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
@RequestMapping("/admin/product")
@Controller
public class AdProductController {

    private final AdProductService adProductService;

    private final BrandService brandService;

    private final CategoryBService categoryBService;

    private final CategoryMService categoryMService;

    private final ProductService productService;

    private final PaginationService paginationService;


    // 상품 리스트
    @GetMapping
    public String productList(@AuthenticationPrincipal Principal principal,
                              @PageableDefault(size = 30) Pageable pageable,
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

        Page<ProductDto> productList = adProductService.getProductList(
                productBrand, productGender, productSize, productGrade, productState,
                productDate, query, pageable);

        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), productList.getTotalPages());
        List<BrandDto> brandList = adProductService.getBrandList();
        List<String> gradeList = productService.gradeList();
        List<String> stateList = productService.stateList();


        mm.addAttribute("paginationBarNumbers", barNumbers);
        mm.addAttribute("productList", productList);
        mm.addAttribute("brandList", brandList);
        mm.addAttribute("gradeList", gradeList);
        mm.addAttribute("stateList", stateList);
        return "/admin/product/product-main";
    }

    // 상품 상세정보
    @GetMapping("/product-detail/{productId}")
    public String productDetail(@PathVariable Long productId,
                                @AuthenticationPrincipal Principal principal,
                                ModelMap mm) {
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }

        AdProductDto productDetail = adProductService.getProductDetail(productId);
        mm.addAttribute("productDetail", productDetail);

        return "/admin/product/product-detail";
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
        List<BrandDto> brandList = adProductService.getBrandList();
        List<CategoryBDto> cateBList= adProductService.getCategoryList();
        List<CategoryMDto> cateMList = categoryMService.getMiddleCategoryList();

        mm.addAttribute("productDetail", productDetail);
        mm.addAttribute("brandList", brandList);
        mm.addAttribute("cateBList", cateBList);
        mm.addAttribute("cateMList", cateMList);
        return "/admin/product/product-detail-update";
    }

    @ResponseBody
    @PostMapping("/save")
    public ResponseEntity<?> productDetailUpdate(@AuthenticationPrincipal Principal principal,
                                              ProductSaveRequest productSaveRequest) throws Exception {
        if (principal.role().getName() != "ROLE_ADMIN") {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("권한없음");
        }

        System.out.println(productSaveRequest);
        adProductService.productCreate(productSaveRequest);
        return ResponseEntity.status(HttpStatus.OK).body("성공");
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
        adProductService.productUpdate(productId, productUpdateRequest);
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
        return "/admin/product/brand";
    }

    // 상품 브랜드 추가 페이지
    @GetMapping("/brand/new")
    public String productBrandCreate(@AuthenticationPrincipal Principal principal)
    {
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }

        return "/admin/product/brand-create-form";
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
        List<CategoryBDto> cateList = categoryBService.categoryList();
        mm.addAttribute("cateList", cateList);
        return "/admin/product/category";
    }

    // 상품 카테고리 추가 페이지
    @GetMapping("/category/new")
    public String productCategoryCreate(@AuthenticationPrincipal Principal principal,ModelMap modelMap)
    {
        List<CategoryBDto> cateBList = categoryBService.categoryList();
        modelMap.addAttribute("cateBList", cateBList);
        return "/admin/product/category-create-form";
    }

    // 상품 카테고리 추가
    @PostMapping("/category/new/create")
    public String productCategoryCreate(@AuthenticationPrincipal Principal principal,
                                             CategoryCreateRequest categoryCreateRequest,ModelMap mm){
        if(categoryCreateRequest.categoryType().equals("big")) {
            if (!categoryBService.bigCategoryExist(categoryCreateRequest.categoryName())) {
                categoryBService.createCategory(categoryCreateRequest);
            } else {
                //메세지박스 문자가 들어가야함 ::중복된 이름의 카테고리가 있습니다.
            }
        }else if(categoryCreateRequest.categoryType().equals("middle"))
        {
            if(!categoryMService.middleCategoryExsist(categoryCreateRequest.categoryName()))
            {
                categoryMService.middleCategoryCreate(categoryCreateRequest.categoryName(),categoryCreateRequest.Bid());
            }else
            {

            }
        }
        return "redirect:/admin/product/category";
    }

    //상품 카테고리 삭제
    @GetMapping("/category/{categoryId}/deleteB")
    public String productCategoryDeleteB(@PathVariable Long categoryId,
                                     @AuthenticationPrincipal Principal principal){
        categoryMService.middelCategoryDeleteByBCategoryId(categoryId);
        categoryBService.bigCategoryDelete(categoryId);

        return "redirect:/admin/product/category";
    }

    //M카테고리 삭제
    @GetMapping("/category/{categoryId}/deleteM")
    public String productCategoryDeleteM(@PathVariable Long categoryId,
                                        @AuthenticationPrincipal Principal principal){

        categoryMService.middleCategoryDeleteById(categoryId);
        return "redirect:/admin/product/category";
    }
    //카테고리 전환
    @ResponseBody
    @PostMapping("/category/changer")
    public List  CategoryChanger(@RequestBody String type, ModelMap map)
    {


        List list = null;

        if(type.equals("{\"check\":\"big\"}"))
        {
            System.out.println("B진입");
            list = categoryBService.categoryList();

        }else if(type.equals("{\"check\":\"middle\"}"))
        {
            System.out.println("M진입");
            list = categoryMService.getMiddleCategoryList();

        }

        return list;
    }

}
