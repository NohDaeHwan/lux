package com.used.lux.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdMainController {

    @GetMapping
    public String test(ModelMap mm) {
        mm.addAttribute("hello","ㅇㅏㄴ녕");
        return "/admin/test";
    }

}
