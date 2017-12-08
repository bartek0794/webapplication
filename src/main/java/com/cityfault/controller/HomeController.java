package com.cityfault.controller;

import com.cityfault.model.Fault;
import com.cityfault.service.FaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private FaultService faultService;

    @RequestMapping("/")
    public String getHomePage() {
      return "index";
    }

    @GetMapping("/international")
    public String getInternationalPage() {
        return "redirect:users";
    }

    @RequestMapping("/defects")
    public String getAllDefects(Model model) {
        model.addAttribute("defects", faultService.getAllFault());
        return "defects";
    }


    @RequestMapping(value = "/defect/{id}", method= RequestMethod.GET)
    public String getOrder(@PathVariable long id, Model model) {
        model.addAttribute("defect", faultService.getFaultById(id));
        return "defect";
    }



}
