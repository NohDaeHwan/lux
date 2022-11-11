package com.used.lux.controller.user;

import com.used.lux.dto.BrandDto;
import com.used.lux.dto.CategoryBDto;
import com.used.lux.dto.CategoryMDto;
import com.used.lux.response.auction.AuctionResponse;
import com.used.lux.response.product.ProductsResponse;
import com.used.lux.service.BrandService;
import com.used.lux.service.CategoryBService;
import com.used.lux.service.CategoryMService;
import com.used.lux.service.ProductService;
import com.used.lux.service.AuctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final ProductService productService;

    private final AuctionService auctionService;

    private final CategoryBService categoryBService;
    private final CategoryMService categoryMService;

    private final BrandService brandService;

    @GetMapping("/")
    public String index() {
        return "/front/index"; // 루트 페이지를 보여줄 뷰 필요
    }

    @GetMapping("/login")
    public String login() {
        return "/front/login"; // 로그인 페이지를 보여줄 뷰 필요
    }

    @GetMapping("/register")
    public String register() {
        return "/front/register"; // 회원가입 페이지를 보여줄 뷰 필요
    }

    @GetMapping("/search")
    public String search(@RequestParam(defaultValue = "") String query, ModelMap mm) {
        List<ProductsResponse> productList = productService.productFind(query).stream()
                .map(ProductsResponse::from).collect(Collectors.toUnmodifiableList());
        List<AuctionResponse> auctionList = auctionService.productFind(query).stream()
                .map(AuctionResponse::from).collect(Collectors.toUnmodifiableList());
        List<CategoryBDto> categoryList = categoryBService.categoryList();
        List<BrandDto> brandList = brandService.brandList();

        mm.addAttribute("productList", productList);
        mm.addAttribute("auctionList", auctionList);
        mm.addAttribute("categoryList", categoryList);
        mm.addAttribute("brandList", brandList);
        return "/front/search"; // 회원가입 페이지를 보여줄 뷰 필요
    }

    @GetMapping("/searchcate/{mcategoryId}")
    public  String searchcate(@PathVariable Long mcategoryId,
                              @RequestParam(defaultValue = "") String productColor,
                              @RequestParam(defaultValue = "") String productBrand,
                              @RequestParam(defaultValue = "") String productGender,
                              @RequestParam(defaultValue = "") String productSize,
                              @RequestParam(defaultValue = "") String productGrade,
                              @RequestParam(defaultValue = "10000000") String maxPrice,
                              @RequestParam(defaultValue = "100000") String minPrice,
                              @RequestParam(defaultValue = "") String query,
                              @PageableDefault(size = 30) Pageable pageable,
                              ModelMap mm) {

        List<CategoryBDto> categoryList = categoryBService.categoryList();
        Page<ProductsResponse> products = productService.frontProductFind(productColor, productBrand, productGender,
                productSize, productGrade, maxPrice, minPrice, query, pageable).map(ProductsResponse::from);
        List<BrandDto> brandList = brandService.brandList();
        CategoryMDto categoryMDto =categoryMService.getMcategoryid(mcategoryId);

        mm.addAttribute("brandList", brandList);
        mm.addAttribute("categoryList", categoryList);
        mm.addAttribute("products", products);
        mm.addAttribute("cateM",categoryMDto );

        System.out.println("function"+categoryMDto);

        System.out.println();

        return "front/searchcate";
    }

}
