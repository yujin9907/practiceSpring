package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public  String home(){
        return "home";
    } // 기본 / 이 경로에 home.html을 띄우겟다는 간단한 코드
}
