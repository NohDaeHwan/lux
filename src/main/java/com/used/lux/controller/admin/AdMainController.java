package com.used.lux.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdMainController {

    @GetMapping
    public String index() {

        return "/admin/index";
    }

    @GetMapping("/userdetail")
    public String a(){

        return "/admin/users-profile";
    }


}
