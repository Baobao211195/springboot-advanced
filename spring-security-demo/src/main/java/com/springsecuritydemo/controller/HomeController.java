package com.springsecuritydemo.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.*;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home(Model model, Principal principal) {
        if(principal != null) {
            model.addAttribute("msg", "Welcome " + principal.getName());
        }
        return "home";
    }
}
