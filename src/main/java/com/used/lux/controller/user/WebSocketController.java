package com.used.lux.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebSocketController {

    @RequestMapping("/auction/21/bidder")
    public String auction() {
        System.out.println("작동");
        return "front/test";
    }

}
