package com.used.lux.controller;

import com.used.lux.dto.security.Principal;
import com.used.lux.request.AuctionBidRequest;
import com.used.lux.response.auction.AuctionResponse;
import com.used.lux.response.auction.AuctionsResponse;
import com.used.lux.service.AuctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuctionController {

    private final AuctionService auctionService;

    @GetMapping("/auction_list")
    public ResponseEntity<Page<AuctionsResponse>> auctionList(
            @PageableDefault(size = 30, sort = "created_at", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<AuctionsResponse> auctions = auctionService.auctionListFind(pageable).map(AuctionsResponse::from);
        return ResponseEntity.status(HttpStatus.OK).body(auctions); // 경매 리스트 페이지
    }

    @GetMapping("/auction_detail/{id}")
    public ResponseEntity<AuctionResponse> auctionDetail(@PathVariable Long id) {
        AuctionResponse auction = AuctionResponse.from(auctionService.auctionFind(id));
        return ResponseEntity.status(HttpStatus.OK).body(auction); // 경매 상세 페이지
    }

    @GetMapping("/auction_result")
    public ResponseEntity<Page<AuctionsResponse>> auctionResult(
            @PageableDefault(size = 30, sort = "created_at", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<AuctionsResponse> auctions = auctionService.auctionResultFind(pageable).map(AuctionsResponse::from);
        return ResponseEntity.status(HttpStatus.OK).body(auctions); // 종료된 경매 리스트 페이지
    }

    @GetMapping("/auction_result/{id}")
    public ResponseEntity<AuctionResponse> auctionResult(@PathVariable Long id) {
        AuctionResponse auction = AuctionResponse.from(auctionService.resultFind(id));
        return ResponseEntity.status(HttpStatus.OK).body(auction); // 종료된 경매 상세 페이지
    }

    @GetMapping("/auction_info")
    public ResponseEntity<Integer> auctionInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(1); // 경매 정보 페이지 View를 보여줘야 함
    }

    @PostMapping("/bid_request/{auctionId}")
    public ResponseEntity<Integer> orderRequest(
            @PathVariable("auctionId") Long auctionId,
            @AuthenticationPrincipal Principal principal,
            AuctionBidRequest auctionBidRequest
    ) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0); // 로그인 페이지로 이동
        }
        Integer result = auctionService.auctionUpdate(auctionId, auctionBidRequest.toDto(), principal.getUsername());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
