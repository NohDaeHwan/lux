package com.used.lux.controller.admin;

import com.used.lux.domain.constant.OrderState;
import com.used.lux.dto.user.order.ProductOrderCancelDto;
import com.used.lux.dto.user.order.ProductOrderDto;
import com.used.lux.dto.security.Principal;
import com.used.lux.request.OrderUpdateRequest;
import com.used.lux.service.PaginationService;
import com.used.lux.service.admin.AdOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/admin/order")
@Controller
public class AdOrderController {

    private final AdOrderService adOrderService;

    private final PaginationService paginationService;

    // 주문 리스트
    @GetMapping
    public String orderList(@AuthenticationPrincipal Principal principal,
            @PageableDefault(size = 30) Pageable pageable,
            @RequestParam(defaultValue = "") String orderState,
            @RequestParam(defaultValue = "") String orderSellType,
            @RequestParam(defaultValue = "2000-01-01") String orderDate,
            @RequestParam(defaultValue = "") String query,
            ModelMap mm) {
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }

        Page<ProductOrderDto> orderList = adOrderService.getOrderList(orderState, orderSellType,
                orderDate, query, pageable);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(),
                orderList.getTotalPages());

        mm.addAttribute("paginationBarNumbers", barNumbers);
        mm.addAttribute("orderList", orderList);
        return "/admin/order";
    }

    // 주문 상세정보
    @GetMapping("/{orderId}")
    public String orderDetail(@PathVariable Long orderId,
            @AuthenticationPrincipal Principal principal,
            ModelMap mm) {
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }

        ProductOrderDto orderDetail = adOrderService.getOrderDetail(orderId);

        if (!orderDetail.orderState().equals(OrderState.CANCELED.name())) {
            ProductOrderCancelDto productOrderCancelDto = adOrderService.getOrderCancel(orderId);
            mm.addAttribute("orderCancelDetail", productOrderCancelDto);
        }

        mm.addAttribute("orderDetail", orderDetail);
        return "/admin/order-detail";
    }

    // 주문 취소 처리
    @PostMapping("/{orderId}/cancel")
    public String orderCancel(@PathVariable Long orderId,
            @AuthenticationPrincipal Principal principal,
            OrderUpdateRequest orderUpdateRequest) {
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }
        adOrderService.updateCancel(orderId, orderUpdateRequest);
        return "redirect:/admin/order/" + orderId;
    }

}
