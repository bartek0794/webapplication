package com.cityfault.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @RequestMapping({"/","home"})
    public String getHomePage() {
        return "home";
    }

    @GetMapping("/international")
    public String getInternationalPage() {
        return "redirect:/";
    }
}
