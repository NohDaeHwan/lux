package com.used.lux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.used.lux.domain.QBrand.brand;

@RequestMapping("/test")
public class unitTestController {

    @GetMapping()
    public String aa()
    {
        brand
        return null;
    }

}
