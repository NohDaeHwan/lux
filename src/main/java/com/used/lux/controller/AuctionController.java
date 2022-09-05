package com.used.lux.controller;

import com.used.lux.dto.AuctionDto;
import com.used.lux.service.AuctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.awt.print.Pageable;

@RequiredArgsConstructor
@Controller
public class AuctionController {

    private AuctionService auctionService;

    @GetMapping("/auction_list")
    public String auctionList(
            @PageableDefault(size = 30) Pageable pageable,
            ModelMap model
    ) {
        Page<AuctionDto> auctions = auctionService.auctionListFind(pageable);
        model.addAttribute("auctions", auctions);
        return "auction/auction_list"; // 뷰 아직 구현 안됨
    }

    @GetMapping("/auction_detail/{id}")
    public String auctionDetail(@PathVariable Long id, ModelMap model) {
        AuctionDto auction = auctionService.auctionFind(id);
        model.addAttribute("auction", auction);
        return "auction/auction_detail"; // 뷰 아직 구현 안됨
    }

    @GetMapping("/auction_result")
    public String auctionResult(ModelMap model, @PageableDefault(size = 30) Pageable pageable) {
        Page<AuctionDto> auctions = auctionService.auctionResultFind(pageable);
        model.addAttribute("auctions", auctions);
        return "auction/auction_result";
    }

    @GetMapping("/auction_result/{id}")
    public String auctionResult(@PathVariable Long id, ModelMap model) {
        AuctionDto auction = auctionService.resultFind(id);
        model.addAttribute("auction", auction);
        return "auction/auction_result_detail";
    }

    @GetMapping("/auction_info")
    public String auctionInfo() {
        return "auction/auction_info";
    }

}
