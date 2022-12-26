package com.used.lux.controller.user;

import com.used.lux.dto.BrandDto;
import com.used.lux.dto.CategoryBDto;
import com.used.lux.dto.UserGradeDto;
import com.used.lux.dto.security.Principal;
import com.used.lux.dto.user.auction.AuctionLogDto;
import com.used.lux.response.auction.AuctionResponse;
import com.used.lux.service.*;
import com.used.lux.service.user.auction.AuctionLogService;
import com.used.lux.service.user.auction.AuctionService;
import com.used.lux.service.BrandService;
import com.used.lux.service.CategoryBService;
import com.used.lux.service.PaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/auction")
@Controller
public class AuctionController {

    private final AuctionService auctionService;

    private final AuctionLogService auctionLogService;

    private final BrandService brandService;

    private final CategoryBService categoryBService;

    private final PaginationService paginationService;

    private final UserGradeService userGradeService;

    @GetMapping
    public String auctionList(@PageableDefault(size = 30) Pageable pageable, ModelMap mm) {
        Page<AuctionResponse> auctions = auctionService.auctionListFind(pageable).map(AuctionResponse::from);
        List<CategoryBDto> categoryList = categoryBService.categoryList();
        List<BrandDto> brandList = brandService.brandList();
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(),
                auctions.getTotalPages());

        mm.addAttribute("paginationBarNumbers", barNumbers);
        mm.addAttribute("auctions", auctions);
        mm.addAttribute("categoryList", categoryList);
        mm.addAttribute("brandList", brandList);

        return "/front/auction";
    }

    @ResponseBody
    @PostMapping("/present/{auctionId}/{stateId}")
    public ResponseEntity<Integer> presentAuction(@PathVariable Long auctionId, @PathVariable Long stateId) {
        auctionService.presentTimer(auctionId, stateId);
        return ResponseEntity.status(HttpStatus.OK).body(1);
    }

    @ResponseBody
    @PostMapping("/after/{auctionId}/{stateId}")
    public ResponseEntity<Integer> afterAuction(@PathVariable Long auctionId, @PathVariable Long stateId) {
        auctionService.afterTimer(auctionId, stateId);
        return ResponseEntity.status(HttpStatus.OK).body(1);
    }

    // 경매 상세 페이지
    @GetMapping("/detail/{auctionId}")
    public String auctionDetail(@PathVariable Long auctionId,
                                @AuthenticationPrincipal Principal principal, ModelMap mm
                                ) {

        List<CategoryBDto> categoryList = categoryBService.categoryList();
        List<UserGradeDto> gradeList = userGradeService.getGradeList();
        AuctionResponse auction = AuctionResponse.from(auctionService.auctionFind(auctionId));
        List<AuctionLogDto> auctionLogList = auctionLogService.auctionLogList(auctionId);

        mm.addAttribute("principal",principal);
        mm.addAttribute("categoryList", categoryList);
        mm.addAttribute("gradeList", gradeList);
        mm.addAttribute("auction",auction);
        mm.addAttribute("auctionLogList", auctionLogList);

        return "/front/auction-detail";
    }

}
