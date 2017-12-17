package com.cityfault.controller;

import com.cityfault.model.Department;
import com.cityfault.service.FaultElementService;
import com.cityfault.service.FaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @Autowired
    private FaultService faultService;
    @Autowired
    private FaultElementService<Department> departmentService;

    @RequestMapping({"/","home"})
    public String getHomePage() {
        return "home";
    }

    @GetMapping("/international")
    public String getInternationalPage() {
        return "redirect:/";
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

    @RequestMapping("/departments")
    public String showAllDepartments() {
        return "/admin/departments";
    }

    @RequestMapping(value = "/department/{id}", method= RequestMethod.GET)
    public String getDepartment(@PathVariable int id, Model model) {
        model.addAttribute("department", departmentService.getById(id));
        return "/admin/department";
    }

}
