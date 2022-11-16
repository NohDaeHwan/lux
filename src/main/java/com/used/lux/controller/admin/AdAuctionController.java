package com.used.lux.controller.admin;

import com.used.lux.dto.StateDto;
import com.used.lux.dto.admin.AdAuctionDto;
import com.used.lux.dto.security.Principal;
import com.used.lux.request.AuctionUpdateRequest;
import com.used.lux.repository.response.auction.AuctionResponse;
import com.used.lux.service.PaginationService;
import com.used.lux.service.StateService;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@RequestMapping("/admin/auction")
@Controller
public class AdAuctionController {

    private final AdAuctionService adAuctionService;

    private final StateService stateService;

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

        Page<AuctionResponse> auctionList = adAuctionService.getAuctionList(auctionState, auctionDate,
                query, pageable).map(AuctionResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), auctionList.getTotalPages());
        List<StateDto> stateList = stateService.getStateList();

        mm.addAttribute("paginationBarNumbers", barNumbers);
        mm.addAttribute("auctionList", auctionList);
        mm.addAttribute("stateList",stateList);
        return "/admin/auction";
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

        if (auctionDetail.auctionDto().stateDto().stateStep().equals("경매전")){
            return  "/admin/auction-create-form";
        }
        return "/admin/auction-detail";
    }

    // 경매 수정 업데이트
    @PostMapping("/{auctionId}/update")
    public String auctionUpdate(@PathVariable Long auctionId,
                                @AuthenticationPrincipal Principal principal,
                                ModelMap mm , AuctionUpdateRequest auctionUpdateRequest){
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }
        System.out.println(auctionUpdateRequest);

        adAuctionService.auctionUpdate(auctionId,auctionUpdateRequest);

        return "redirect:/admin/auction/{auctionId}";
    }

}
