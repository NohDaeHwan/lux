package com.used.lux.controller.user;
import com.used.lux.dto.BrandDto;
import com.used.lux.dto.CategoryBDto;
import com.used.lux.dto.CategoryMDto;

import com.used.lux.dto.JoinMemberDto;
import com.used.lux.repository.response.auction.AuctionResponse;
import com.used.lux.repository.response.product.ProductResponse;


import com.used.lux.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class MainController {
    private final UserAccountService userAccountService;
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

    @PostMapping("/login_request")
    public String addUser(@Valid JoinMemberDto joinMemberDto , Errors errors,ModelMap mm){

        //정규식을 이용한 체크
        if(errors.hasErrors())
        {
            if(errors.hasFieldErrors("userName"))
            {mm.addAttribute("userName", errors.getFieldError("userName").getDefaultMessage());}
            if(errors.hasFieldErrors("password"))
            {mm.addAttribute("password",errors.getFieldError("password").getDefaultMessage());}
            if(errors.hasFieldErrors("phoneNumber"))
            {mm.addAttribute("phoneNumber",errors.getFieldError("phoneNumber").getDefaultMessage());}
            if(errors.hasFieldErrors("name"))
            {mm.addAttribute("name",errors.getFieldError("name").getDefaultMessage());}
            if(errors.hasFieldErrors("age"))
            {mm.addAttribute("age",errors.getFieldError("age").getDefaultMessage());}
        }
        //비밀번호 중복체크
        if(!joinMemberDto.getPassword().equals(joinMemberDto.getPasswordRepeat()))
        {mm.addAttribute("passwordRepeat","notMatchPassword");}
        //이메일 중복체크
        if(userAccountService.exsistByUserEmail(joinMemberDto.getUserName()))
        {mm.addAttribute("userName","duplicationEmail");}


        if(!mm.isEmpty()){
            mm.addAttribute("joinMemberDto",joinMemberDto);
            return "/front/register";
        }
        return "/front/login";
    }

    @GetMapping("/search")
    public String search(@RequestParam(defaultValue = "") String query, ModelMap mm) {
        List<ProductResponse> productList = productService.productFind(query).stream()
                .map(ProductResponse::from).collect(Collectors.toUnmodifiableList());
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
                              @RequestParam(defaultValue = "1000") String minPrice,
                              @RequestParam(defaultValue = "") String query,
                              @PageableDefault(size = 30) Pageable pageable,
                              ModelMap mm) {

        List<CategoryBDto> categoryList = categoryBService.categoryList();
//        Page<ProductsResponse> products = productService.frontProductFind(productColor, productBrand, productGender,
//                productSize, productGrade, maxPrice, minPrice, query, pageable).map(ProductsResponse::from);
        List<BrandDto> brandList = brandService.brandList();
        CategoryMDto categoryMDto =categoryMService.getMcategoryid(mcategoryId);

        List<ProductResponse> products = productService.catesearch(mcategoryId ,productColor,productBrand,productGender,
                productSize,productGrade,maxPrice,minPrice,query,pageable).stream().map(ProductResponse::from).collect(Collectors.toUnmodifiableList());

        List<AuctionResponse> auctions= auctionService.catesearch(mcategoryId ,productColor,productBrand,productGender,
                productSize,productGrade,maxPrice,minPrice,query,pageable).stream().map(AuctionResponse::from).collect(Collectors.toUnmodifiableList());

        List<AuctionResponse> auction= auctionService.searchcate(productBrand,mcategoryId,productColor,productGender,productSize,maxPrice,minPrice).stream().map(AuctionResponse::from).collect(Collectors.toList());


        mm.addAttribute("brandList", brandList);
        mm.addAttribute("categoryList", categoryList);
        mm.addAttribute("products", products);
        mm.addAttribute("cateM",categoryMDto );
        mm.addAttribute("auctions",auctions);
        mm.addAttribute("a",auction);

        System.out.println("function"+categoryMDto);




        System.out.println("중고 검색:"+products.stream().toList());
        System.out.println("경매 검색:"+auctions.stream().toList());
        System.out.println("실험 :"+ auction.stream().toList());

        return "front/searchcate";
    }

}
