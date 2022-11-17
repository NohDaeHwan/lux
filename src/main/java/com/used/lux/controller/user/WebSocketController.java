package com.used.lux.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebSocketController {

    @RequestMapping("/auction/{auctionId}/bidder")
    public String auction(@PathVariable Long auctionId) {
        System.out.println("작동 : " + auctionId);
        return "/admin/auction-detail";
    }

}
