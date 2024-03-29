package com.used.lux.controller.admin;

import com.used.lux.dto.admin.AdAuctionDto;
import com.used.lux.dto.security.Principal;
import com.used.lux.dto.user.auction.AuctionDto;
import com.used.lux.request.auction.AuctionUpdateRequest;
import com.used.lux.service.PaginationService;
import com.used.lux.service.admin.AdAuctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/admin/auction")
@Controller
public class AdAuctionController {

    private final AdAuctionService adAuctionService;

    private final PaginationService paginationService;

    // 경매 리스트
    @GetMapping
    public String auctionList(@AuthenticationPrincipal Principal principal,
                              @PageableDefault(size = 30) Pageable pageable,
                              @RequestParam(defaultValue = "") String auctionState,
                              @RequestParam(defaultValue = "") String auctionDate,
                              @RequestParam(defaultValue = "") String query,
                              ModelMap mm){
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }

        Page<AuctionDto> aucList = adAuctionService.getAuctionList(auctionState, auctionDate, query, pageable);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), aucList.getTotalPages());

        mm.addAttribute("paginationBarNumbers", barNumbers);
        mm.addAttribute("aucList", aucList);
        return "/admin/auction/auction-main";
    }

     //경매 상세정보
    @GetMapping("/{auctionId}")
    public String auctionDetail(@PathVariable Long auctionId,
                                @AuthenticationPrincipal Principal principal,
                                ModelMap mm){
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }
        AdAuctionDto auctionDetail = adAuctionService.getAuctionDetail(auctionId);

        mm.addAttribute("auctionDetail", auctionDetail);
        return "/admin/auction/auction-detail";
    }

    // 경매 수정 업데이트
    @PostMapping("/{auctionId}/update")
    public String auctionUpdate(@PathVariable Long auctionId,
                                @AuthenticationPrincipal Principal principal,
                                AuctionUpdateRequest auctionUpdateRequest){
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }
        adAuctionService.auctionUpdate(auctionId,auctionUpdateRequest);

        return "redirect:/admin/auction/{auctionId}";
    }

}
