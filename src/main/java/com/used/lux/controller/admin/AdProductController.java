package com.used.lux.controller.admin;

import com.used.lux.dto.security.Principal;
import com.used.lux.response.product.ProductResponse;
import com.used.lux.service.AdProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/admin/product")
@Controller
public class AdProductController {

    private final AdProductService adProductService;

    // 상품 리스트
    @GetMapping
    public String appraise(@AuthenticationPrincipal Principal principal,
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

}