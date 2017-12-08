package com.cityfault.controller;

import com.cityfault.model.Department;
import com.cityfault.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/addDepartment")
    public String addDepartment(Model model) {
        model.addAttribute("department", new Department());
        return "/admin/addDepartment";
    }

    @PostMapping("/addDepartment")
    public String addDepartment(@Valid @ModelAttribute("department") Department department, BindingResult bindingResult) {
        if(departmentService.getDepartmentByName(department.getDepartmentName()) != null) {
            ObjectError error = new ObjectError("unique", "Department name already exists!");
            bindingResult.addError(error);
        }

        if (bindingResult.hasErrors()) {
            return "/admin/addDepartment";
        }

        departmentService.saveDepartment(department);
        return "redirect:departments";
    }
}
