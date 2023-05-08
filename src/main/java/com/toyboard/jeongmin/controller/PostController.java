package com.toyboard.jeongmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts")
public class PostController {

    @GetMapping("/")
    public String getPost() {
        return "Hello";
    }

}
