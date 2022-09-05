package com.used.lux.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index"; // 뷰 아직 구현 안됨
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // 뷰 아직 구현 안됨
    }

}
