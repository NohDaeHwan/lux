package com.used.lux.controller;

import com.used.lux.categoryCAD.brandCAD;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@RequestMapping("/test")
@RestController
public class unitTestController {
    private final brandCAD cad;
    @GetMapping("/test")
    @Transactional
    public String aa()
    {
        String i = String.valueOf(cad.brandDelete("가늬5"));
        return i;
    }

}
