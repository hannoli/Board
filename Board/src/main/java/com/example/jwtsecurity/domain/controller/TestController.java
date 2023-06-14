package com.example.jwtsecurity.domain.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PostMapping("/test")
    public String test(){

        return "<h1>test 통과</h1>";
    }

    @GetMapping("/test")
    public String test1(){

        return "<h1>test 통과</h1>";
    }
    @GetMapping("/board")
    public String board(){

        return "<h1>보드</h1>";
    }
}