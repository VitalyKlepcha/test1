package com.example.test1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DatabaseController {
    @PostMapping("/home")
    public String goToHome(Model model){
        return "home";
    }
}
