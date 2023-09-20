package com.used.lux.controller.user;

import com.used.lux.domain.constant.RoleType;
import com.used.lux.domain.useraccount.UserAccount;
import com.used.lux.dto.*;
import com.used.lux.dto.user.appraisal.AppraisalDto;
import com.used.lux.dto.user.auction.AuctionDto;
import com.used.lux.dto.user.product.ProductDto;
import com.used.lux.response.auction.AuctionResponse;
import com.used.lux.response.product.ProductResponse;
import com.used.lux.service.BrandService;
import com.used.lux.service.CategoryBService;
import com.used.lux.service.CategoryMService;
import com.used.lux.service.UserGradeService;
import com.used.lux.service.user.appraisal.AppraiseService;
import com.used.lux.service.user.product.ProductService;
import com.used.lux.service.user.auction.AuctionService;
import com.used.lux.service.user.useraccount.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final UserAccountService userAccountService;

    private final ProductService productService;

    private final AuctionService auctionService;

    private  final AppraiseService appraiseService;

    private final CategoryBService categoryBService;
    private final CategoryMService categoryMService;

    private final BrandService brandService;

    private final UserGradeService userGradeService;

    private final PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String index(ModelMap mm) {
        List<AuctionDto> auctions = auctionService.findByState10AndRecent4List();
        List<ProductDto> products = productService.findByState6AndRecent4List();

        mm.addAttribute("auctions",auctions);
        mm.addAttribute("products",products);
        return "/front/index";
        // 루트 페이지를 보여줄 뷰 필요
    }

    @GetMapping("/login")
    public String login() {
        return "/front/login";
    }

    @GetMapping("/register")
    public String register(ModelMap mm) {
        JoinMemberDto joinMemberDto = new JoinMemberDto();

        mm.addAttribute("joinMemberDto",joinMemberDto);
        return "/front/register";
    }

    @PostMapping("/join_request")
    public String addUser(@Valid JoinMemberDto joinMemberDto , Errors errors, ModelMap mm){

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

        userAccountService.addUser(UserAccount.builder()
                .userName(joinMemberDto.getUserName())
                .userPassword(passwordEncoder.encode(joinMemberDto.getPassword()))
                .userName(joinMemberDto.getName())
                .phoneNumber(joinMemberDto.getPhoneNumber())
                .age(Integer.parseInt(joinMemberDto.getAge()))
                .gender(joinMemberDto.getGender())
                .userGrade(userGradeService.getGradeName(1))
                .role(RoleType.USER)
                .memo("TEST USER")
                .build());
        return "redirect:/login";

    }

    @GetMapping("/search")
    public String search(@RequestParam(defaultValue = "") String query, ModelMap mm) {
        List<ProductDto> productList = productService.productFind(query);
        List<AuctionDto> auctionList = auctionService.productFind(query);
        List<AppraisalDto> appraisalResponseList=appraiseService.productFind(query);
        List<CategoryBDto> categoryList = categoryBService.categoryList();
        List<BrandDto> brandList = brandService.brandList();

        mm.addAttribute("productList", productList);
        mm.addAttribute("auctionList", auctionList);
        mm.addAttribute("categoryList", categoryList);
        mm.addAttribute("brandList", brandList);
        mm.addAttribute("appraisalResponseList",appraisalResponseList);

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

        List<BrandDto> brandList = brandService.brandList();
        CategoryMDto categoryMDto =categoryMService.getMcategoryid(mcategoryId);

        List<ProductDto> products = productService.catesearch(mcategoryId,productColor,productBrand,productGender,
                productSize,productGrade,maxPrice,minPrice,query);

        List<AuctionDto> auction= auctionService.searchcate(mcategoryId,productBrand,productColor,productGender,
                productSize,productGrade,maxPrice,minPrice,query);

        mm.addAttribute("brandList", brandList);
        mm.addAttribute("categoryList", categoryList);
        mm.addAttribute("products", products);
        mm.addAttribute("cateM",categoryMDto );
        mm.addAttribute("a",auction);

        return "front/searchcate";
    }

}
