package com.used.lux.controller.user;
import com.used.lux.domain.Auction;
import com.used.lux.domain.Product;
import com.used.lux.domain.UserAccount;
import com.used.lux.domain.UserGrade;
import com.used.lux.domain.constant.RoleType;
import com.used.lux.dto.*;

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

    private final UserGradeService userGradeService;

    @GetMapping("/")
    public String index(ModelMap mm) {
        List<AuctionResponse> auctions = auctionService.findByState10AndRecent4List().stream()
                .map(AuctionResponse::from).collect(Collectors.toUnmodifiableList());
        List<ProductResponse> products = productService.findByState6AndRecent4List().stream()
                .map(ProductResponse::from).collect(Collectors.toUnmodifiableList());;
        System.out.println(auctions);
        System.out.println(products);
        mm.addAttribute("auctions",auctions);
        mm.addAttribute("products",products);
        return "/front/index";
        // 루트 페이지를 보여줄 뷰 필요
    }

    @GetMapping("/login")
    public String login() {
        return "/front/login"; // 로그인 페이지를 보여줄 뷰 필요
    }

    @GetMapping("/register")
    public String register(ModelMap mm) {
        JoinMemberDto joinMemberDto = new JoinMemberDto();
        mm.addAttribute("joinMemberDto",joinMemberDto);
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

        //이메일,닉네임,휴대폰 번호 중복체크
        if(userAccountService.exsistByUserEmail(joinMemberDto.getUserName()))
        {mm.addAttribute("userName","duplicationEmail");}
        if(userAccountService.exsistByUserName(joinMemberDto.getName()))
        {mm.addAttribute("name","duplicationUserName");}
        if(userAccountService.exsistByPhoneNumber(joinMemberDto.getPhoneNumber()))
        {mm.addAttribute("phoneNumber","duplicationPhoneNumber");}
        System.out.println("=================================================================");

        //제약 조건 전부 통과 못하면 기존값 유지하고 refresh
        if(mm.size()>2){
            System.out.println("nulllllll");
            mm.addAttribute("joinMemberDto",joinMemberDto);
            return "/front/register";
        }

            UserGrade userGrade = userGradeService.getGradeName(1);
            UserAccount userAccount = UserAccount.of(null,
                    joinMemberDto.getUserName(), joinMemberDto.getPassword(), joinMemberDto.getName(), joinMemberDto.getPhoneNumber(), Integer.parseInt(joinMemberDto.getAge()), joinMemberDto.getGender(),0,userGrade, RoleType.USER,"TEST USER" );

            System.out.println("회원가입완료 ");
            userAccountService.addUser(userAccount);
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
