package com.example.test1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ErrorController {
    @GetMapping("/{errorPage}")
    public String missingPage(@PathVariable(value = "errorPage") String page, Model model) {
        return "404";
    }
    @PostMapping("/{errorPage}")
    public String goToHome(@PathVariable(value = "errorPage") String page,Model model){
        return "home";
    }
}

