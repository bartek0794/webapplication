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
            model.addAttribute("faultElement", new Department());
            model.addAttribute("type", "Department");
        }
        else if(type.equals("status")) {
            model.addAttribute("faultElement", new Status());
            model.addAttribute("type", "Status");
        }
        else if(type.equals("priority")) {
            model.addAttribute("faultElement", new Priority());
            model.addAttribute("type", "Priority");
        }
        return "/admin/addDefectElement";
    }

    @PostMapping("/add/Department")
    public String addDepartment(@Valid @ModelAttribute("faultElement") Department department, BindingResult bindingResult, Model model) {
        if(departmentService.getByName(department.getName()) != null) {
            ObjectError error = new ObjectError("unique", "Department name already exists!");
            bindingResult.addError(error);
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("type", "Department");
            return "/admin/addDefectElement";
        }
        departmentService.saveFaultElement(department);

        return "redirect:/defectElements/department";
    }

    @PostMapping("/add/Status")
    public String addDepartment(@Valid @ModelAttribute("faultElement") Status status, BindingResult bindingResult, Model model) {
        if(departmentService.getByName(status.getName()) != null) {
            ObjectError error = new ObjectError("unique", "Status name already exists!");
            bindingResult.addError(error);
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("type", "Status");
            return "/admin/addDefectElement";
        }
        statusService.saveFaultElement(status);

        return "redirect:/defectElements/status";
    }

    @PostMapping("/add/Priority")
    public String addPriority(@Valid @ModelAttribute("faultElement") Priority priority, BindingResult bindingResult, Model model) {
        if(departmentService.getByName(priority.getName()) != null) {
            ObjectError error = new ObjectError("unique", "Priority name already exists!");
            bindingResult.addError(error);
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("type", "Priority");
            return "/admin/addDefectElement";
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

    @PostMapping("/saveDefectElement/Department")
    public String saveDepartment(HttpServletRequest request) {
        int id = Integer.valueOf(request.getParameter("defectElementId"));

        Department department = departmentService.getById(id);
        department.setName(request.getParameter("defectElementName"));

        departmentService.saveFaultElement(department);

        return "redirect:/defectElement/Department/" + id;
    }

    @PostMapping("/saveDefectElement/Status")
    public String saveStatus(HttpServletRequest request) {
        int id = Integer.valueOf(request.getParameter("defectElementId"));

        Status status = statusService.getById(id);
        status.setName(request.getParameter("defectElementName"));

        statusService.saveFaultElement(status);

        return "redirect:/defectElement/Status/" + id;
    }

    @PostMapping("/saveDefectElement/Priority")
    public String savePriority(HttpServletRequest request) {
        int id = Integer.valueOf(request.getParameter("defectElementId"));

        Priority priority = priorityService.getById(id);
        priority.setName(request.getParameter("defectElementName"));

        priorityService.saveFaultElement(priority);

        return "redirect:/defectElement/Priority/" + id;
    }

}