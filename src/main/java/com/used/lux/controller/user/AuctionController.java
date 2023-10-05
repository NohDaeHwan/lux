package com.used.lux.controller.user;

import com.used.lux.dto.BrandDto;
import com.used.lux.dto.CategoryBDto;
import com.used.lux.dto.UserGradeDto;
import com.used.lux.dto.security.Principal;
import com.used.lux.dto.user.auction.AuctionDto;
import com.used.lux.dto.user.auction.AuctionLogDto;
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
        Page<AuctionDto> aucList = auctionService.auctionListFind(pageable);
        List<CategoryBDto> cateList = categoryBService.categoryList();
        List<BrandDto> brandList = brandService.brandList();
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(),
                aucList.getTotalPages());

        mm.addAttribute("paginationBarNumbers", barNumbers);
        mm.addAttribute("aucList", aucList);
        mm.addAttribute("cateList", cateList);
        mm.addAttribute("brandList", brandList);

        return "/front/auction/auction-main";
    }

    @ResponseBody
    @PostMapping("/present/{auctionId}/{aucState}")
    public ResponseEntity<Integer> presentAuction(@PathVariable Long auctionId, @PathVariable String aucState) {
        auctionService.presentTimer(auctionId, aucState);
        return ResponseEntity.status(HttpStatus.OK).body(1);
    }

    @ResponseBody
    @PostMapping("/after/{auctionId}/{aucState}")
    public ResponseEntity<Integer> afterAuction(@PathVariable Long auctionId, @PathVariable String aucState) {
        auctionService.afterTimer(auctionId, aucState);
        return ResponseEntity.status(HttpStatus.OK).body(1);
    }

    // 경매 상세 페이지
    @GetMapping("/{auctionId}")
    public String auctionDetail(@PathVariable Long auctionId,
                                @AuthenticationPrincipal Principal principal, ModelMap mm
    ) {
        List<CategoryBDto> cateList = categoryBService.categoryList();
        List<UserGradeDto> gradeList = userGradeService.getGradeList();
        AuctionDto auc = auctionService.auctionFind(auctionId);
        List<AuctionLogDto> aucLogList = auctionLogService.auctionLogList(auctionId);

        mm.addAttribute("principal", principal);
        mm.addAttribute("cateList", cateList);
        mm.addAttribute("gradeList", gradeList);
        mm.addAttribute("auc", auc);
        mm.addAttribute("aucLogList", aucLogList);

        return "/front/auction/auction-detail";
    }

}
