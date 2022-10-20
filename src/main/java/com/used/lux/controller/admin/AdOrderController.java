package com.used.lux.controller.admin;

import com.used.lux.dto.admin.AdProductOrderDto;
import com.used.lux.dto.security.Principal;
import com.used.lux.response.ProductOrderResponse;
import com.used.lux.service.admin.AdOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/admin/order")
@Controller
public class AdOrderController {

    private final AdOrderService adOrderService;

    // 주문 리스트
    @GetMapping
    public String orderList(@AuthenticationPrincipal Principal principal,
                              @PageableDefault(size = 30) Pageable pageable,
                              ModelMap mm){
        /*if (principal == null) {
            return "redirect:/login";
        }
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }*/
        Page<ProductOrderResponse> orderList = adOrderService.getOrderList(pageable).map(ProductOrderResponse::from);
        mm.addAttribute("orderList", orderList);
        return "/admin/order";
    }

    // 주문 상세정보
    @GetMapping("/{orderId}")
    public String orderDetail(@PathVariable Long orderId,
                                @AuthenticationPrincipal Principal principal,
                                ModelMap mm){
        /*if (principal == null) {
            return "redirect:/login";
        }
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }*/
        AdProductOrderDto orderDetail = adOrderService.getOrderDetail(orderId);
        mm.addAttribute("orderDetail", orderDetail);
        return "/admin/order/order-detail";
    }

}
