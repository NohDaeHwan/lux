package com.used.lux.controller.admin;

import com.used.lux.dto.admin.AdAuctionDto;
import com.used.lux.dto.security.Principal;
import com.used.lux.request.AuctionUpdateRequest;
import com.used.lux.response.auction.AuctionResponse;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@RequestMapping("/admin/auction")
@Controller
public class AdAuctionController {

    private final AdAuctionService adAuctionService;

    // 경매 리스트
    @GetMapping
    public String auctionList(@AuthenticationPrincipal Principal principal,
                           @PageableDefault(size = 30) Pageable pageable,
                           ModelMap mm){
        /*if (principal == null) {
            return "redirect:/login";
        }
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }*/
        Page<AuctionResponse> auctionList = adAuctionService.getAuctionList(pageable).map(AuctionResponse::from);
        mm.addAttribute("auctionList", auctionList);
        return "/admin/auction";
    }

     //경매 상세정보
    @GetMapping("/{auctionId}")
    public String auctionDetail(@PathVariable Long auctionId,
                                @AuthenticationPrincipal Principal principal,
                                ModelMap mm){
        /*if (principal == null) {
            return "redirect:/login";
        }
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }*/
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
                                ModelMap mm ,AuctionUpdateRequest auctionUpdateRequest){
        /*if (principal == null) {
            return "redirect:/login";
        }
        if (principal.role().getName() != "ROLE_ADMIN") {
            return "redirect:/";
        }*/

        adAuctionService .auctionUpdate(auctionId,auctionUpdateRequest);
        System.out.println("페이지 갔다옴");

        String str = auctionUpdateRequest.auctionClosingDate();
        str = str.replaceAll("T", " ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);


        System.out.println(auctionUpdateRequest);

        return "redirect:/admin/auction/{auctionId}";
    }

}
