package com.used.lux.controller.admin;

import com.used.lux.domain.CategoryB;
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

    // 상품 상세정보
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
        List<CategoryB> BCategoryList = categoryBService.getBigCategoryAll();
        mm.addAttribute("categoryList", BCategoryList);
        return "/admin/category";
    }

    // 상품 카테고리 추가 페이지
    @GetMapping("/category/new")
    public String productCategoryCreate(@AuthenticationPrincipal Principal principal)
    {

        return "/admin/category-create-form";
    }

    // 상품 카테고리 추가
    @PostMapping("/category/new/create")
    public String productCategoryCreate(@AuthenticationPrincipal Principal principal,
                                             CategoryCreateRequest categoryCreateRequest,ModelMap mm){

        if(!categoryBService.bigCategoryExist(categoryCreateRequest.categoryName()))
        {
            categoryBService.createCategory(categoryCreateRequest);
        }else
        {
            //메세지박스 문자가 들어가야함 ::중복된 이름의 카테고리가 있습니다.
        }

        return "redirect:/admin/product/category";
    }
    //상품 카테고리 삭제
    @GetMapping("/category/{categoryId}/delete")
    public String productCategoryDelete(@PathVariable Long categoryId,
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

    //카테고리 전환
    @ResponseBody
    @GetMapping("/category/changer")
    public List CategoryChanger(@RequestParam("categoryType")String type, ModelMap map)
    {
        List list = null;
        if(type.equals("big"))
        {
            list = categoryBService.getBigCategoryAll();
        }else if(type.equals("middle"))
        {
            list =categoryMService.getMiddleCategoryList();
        }

        return list;
    }


}
