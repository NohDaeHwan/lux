package com.used.lux.controller.user;

import com.used.lux.dto.BrandDto;
import com.used.lux.dto.CategoryBDto;
import com.used.lux.repository.response.auction.AuctionResponse;
import com.used.lux.repository.response.auction.AuctionsResponse;
import com.used.lux.service.BrandService;
import com.used.lux.service.CategoryBService;
import com.used.lux.service.PaginationService;
import com.used.lux.service.AuctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/auction")
@Controller
public class AuctionController {

    private final AuctionService auctionService;

    private final BrandService brandService;

    private final CategoryBService categoryBService;

    private final PaginationService paginationService;

    @GetMapping
    public  String auctionList(@PageableDefault(size = 30) Pageable pageable, ModelMap mm)
    {
        Page<AuctionResponse> auctions = auctionService.auctionListFind(pageable).map(AuctionResponse::from);
        List<CategoryBDto> categoryList = categoryBService.categoryList();
        List<BrandDto> brandList = brandService.brandList();
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), auctions.getTotalPages());

        mm.addAttribute("paginationBarNumbers", barNumbers);
        mm.addAttribute("auctions", auctions);
        mm.addAttribute("categoryList", categoryList);
        mm.addAttribute("brandList", brandList);
        return "/front/auction";
    }

//    @GetMapping("/detail/{id}")
//    public ResponseEntity<AuctionResponse> auctionDetail(@PathVariable Long id) {
//        AuctionResponse auction = AuctionResponse.from(auctionService.auctionFind(id));
//        return ResponseEntity.status(HttpStatus.OK).body(auction); // 경매 상세 페이지
//    }
//
//    @GetMapping("/result")
//    public ResponseEntity<Page<AuctionsResponse>> auctionResult(
//            @PageableDefault(size = 30, sort = "created_at", direction = Sort.Direction.DESC) Pageable pageable
//    ) {
//        Page<AuctionsResponse> auctions = auctionService.auctionResultFind(pageable).map(AuctionsResponse::from);
//        return ResponseEntity.status(HttpStatus.OK).body(auctions); // 종료된 경매 리스트 페이지
//    }
//
//    @GetMapping("/result/detail/{id}")
//    public ResponseEntity<AuctionResponse> auctionResult(@PathVariable Long id) {
//        AuctionResponse auction = AuctionResponse.from(auctionService.resultFind(id));
//        return ResponseEntity.status(HttpStatus.OK).body(auction); // 종료된 경매 상세 페이지
//    }
//
//    @GetMapping("/info")
//    public ResponseEntity<Integer> auctionInfo() {
//        return ResponseEntity.status(HttpStatus.OK).body(1); // 경매 정보 페이지 View를 보여줘야 함
//    }
//
//    @PostMapping("/bid_request/{auctionId}")
//    public ResponseEntity<Integer> orderRequest(
//            @PathVariable("auctionId") Long auctionId,
//            @AuthenticationPrincipal Principal principal,
//            AuctionBidRequest auctionBidRequest
//    ) {
//        if (principal == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0); // 로그인 페이지로 이동
//        }
//        Integer result = auctionService.auctionUpdate(auctionId, auctionBidRequest.toDto(), principal.getUsername());
//        return ResponseEntity.status(HttpStatus.OK).body(result);
//    }

}
