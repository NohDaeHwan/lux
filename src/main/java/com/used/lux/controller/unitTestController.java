package com.used.lux.controller;

import com.used.lux.categoryCAD.*;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.transaction.Transactional;

@RequiredArgsConstructor
@RequestMapping("/test")
@RestController

public class unitTestController {
    private final bigCategoryCAD bigCategoryCAD;


    @GetMapping("/test")
    @Transactional
    public String aa()
    {
        bigCategoryCAD.bigCategoryDelete("111");

        return "성공";
    }

}
