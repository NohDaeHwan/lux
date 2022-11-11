package com.used.lux.controller.admin;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.used.lux.domain.CategoryB;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
            List<CategoryMDto> categoryMDtos = categoryMService.getMiddleCategoryList();

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
        List<CategoryMDto> categoryMDtos = categoryMService.getMiddleCategoryList();

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
        List<CategoryBDto> BCategoryList = categoryBService.getBigCategoryAll();
        mm.addAttribute("categoryList", BCategoryList);
        return "/admin/category";
    }

    // 상품 카테고리 추가 페이지
    @GetMapping("/category/new")
    public String productCategoryCreate(@AuthenticationPrincipal Principal principal,ModelMap modelMap)
    {
        List<CategoryBDto> categoryBDtos = categoryBService.getBigCategoryAll();
        modelMap.addAttribute("listDtos",categoryBDtos);
        return "/admin/category-create-form";
    }

    // 상품 카테고리 추가
    @PostMapping("/category/new/create")
    public String productCategoryCreate(@AuthenticationPrincipal Principal principal,
                                             CategoryCreateRequest categoryCreateRequest,ModelMap mm){
        System.out.println(categoryCreateRequest.categoryType());
        System.out.println(categoryCreateRequest.categoryName());
        System.out.println(categoryCreateRequest.Bid());
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
        //bigcategory 삭제 메소드 1차 ::하위카테고리 삭제여부
        List<String> list = categoryMService.middlecategoryExsistByBCategory(categoryId);

        /*
            메세지 Dto를 만들어서 넣어야함 ::삭제하려는 카테고리와 관계된 카테고리가 있습니다.
                                            해당 카테고리를 삭제하면 다른 카테고리들도 삭제가 됩니다.
                                            그래도 삭제하시겠습니까?
         */

        //bigcategory 삭제 메소드 2차 :: 하위카테고리가 없거나 확인하고 이를 요청한 경우 실행한다
        //B카테고리에 종속된 M카테고리 먼저 제거한다
        categoryMService.middelCategoryDeleteByBCategoryId(categoryId);

        //B카테고리 제거
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
            list = categoryBService.getBigCategoryAll();

        }else if(type.equals("{\"check\":\"middle\"}"))
        {
            System.out.println("M진입");
            list = categoryMService.getMiddleCategoryList();

        }

        return list;
    }


}
