package com.used.lux.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public ResponseEntity<Integer> index() {return ResponseEntity.status(HttpStatus.OK).body(1); // 루트 페이지를 보여줄 뷰 필요
    }

    @GetMapping("/login")
    public ResponseEntity<Integer> login() {
        return ResponseEntity.status(HttpStatus.OK).body(1); // 로그인 페이지를 보여줄 뷰 필요
    }

}
