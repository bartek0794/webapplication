package com.cityfault.controller;

import com.cityfault.model.Department;
import com.cityfault.model.Priority;
import com.cityfault.model.Status;
import com.cityfault.service.FaultElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class FaultElementController {
    @Autowired
    FaultElementService<Department> departmentService;
    @Autowired
    FaultElementService<Status> statusService;
    @Autowired
    FaultElementService<Priority> priorityService;

    @GetMapping("/addDefectElement/{type}")
    public String addDepartment(@PathVariable String type, Model model) {
        if(type.equals("department")) {
            model.addAttribute("defectElement", new Department());
            model.addAttribute("type", "Department");
        }
        else if(type.equals("status")) {
            model.addAttribute("defectElement", new Status());
            model.addAttribute("type", "Status");
        }
        else if(type.equals("priority")) {
            model.addAttribute("defectElement", new Priority());
            model.addAttribute("type", "Priority");
        }
        return "/admin/addDefectElement";
    }

    @PostMapping("/save/Department")
    public String addDepartment(@Valid @ModelAttribute("defectElement") Department department, BindingResult bindingResult, Model model, HttpServletRequest request) {
        if(departmentService.getByName(department.getName()) != null) {
            ObjectError error = new ObjectError("unique", "Department name already exists!");
            bindingResult.addError(error);
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("type", "Department");
            if(request.getParameter("action").equals("add")) {
                return "/admin/addDefectElement";
            } else {
                return "/admin/defectElement";
            }
        }
        departmentService.saveFaultElement(department);
        return "redirect:/defectElements/department";
    }

    @PostMapping("/save/Status")
    public String addDepartment(@Valid @ModelAttribute("defectElement") Status status, BindingResult bindingResult, Model model, HttpServletRequest request) {
        if(statusService.getByName(status.getName()) != null) {
            ObjectError error = new ObjectError("unique", "Status name already exists!");
            bindingResult.addError(error);
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("type", "Status");
            if(request.getParameter("action").equals("add")) {
                return "/admin/addDefectElement";
            } else {
                return "/admin/defectElement";
            }
        }
        statusService.saveFaultElement(status);
        return "redirect:/defectElements/status";
    }

    @PostMapping("/save/Priority")
    public String addPriority(@Valid @ModelAttribute("defectElement") Priority priority, BindingResult bindingResult, Model model, HttpServletRequest request) {
        if(priorityService.getByName(priority.getName()) != null) {
            ObjectError error = new ObjectError("unique", "Priority name already exists!");
            bindingResult.addError(error);
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("type", "Priority");
            if(request.getParameter("action").equals("add")) {
                return "/admin/addDefectElement";
            } else {
                return "/admin/defectElement";
            }
        }
        priorityService.saveFaultElement(priority);
        return "redirect:/defectElements/priority";
    }

    @RequestMapping("/defectElements/{type}")
    public String showAllDepartments(@PathVariable String type, Model model) {
        model.addAttribute("type", type);
        return "/admin/defectElements";
    }

    @RequestMapping(value = "/defectElement/{type}/{id}", method= RequestMethod.GET)
    public String getDepartment(@PathVariable int id, @PathVariable String type, Model model) {
        model.addAttribute("defectElement", departmentService.getById(id));
        model.addAttribute("type", type);
        return "/admin/defectElement";
    }
}