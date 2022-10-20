package com.used.lux.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index"; // 루트 페이지를 보여줄 뷰 필요
    }

    @GetMapping("/login")
    public String login() {
        return "/admin/login"; // 로그인 페이지를 보여줄 뷰 필요
    }

}
