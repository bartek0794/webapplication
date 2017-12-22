package com.cityfault.controller;

import com.cityfault.model.Department;
import com.cityfault.model.Fault;
import com.cityfault.model.Priority;
import com.cityfault.model.Status;
import com.cityfault.service.FaultElementService;
import com.cityfault.service.FaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @Autowired
    private FaultService faultService;
    @Autowired
    private FaultElementService<Department> departmentService;
    @Autowired
    private FaultElementService<Priority> priorityService;
    @Autowired
    private FaultElementService<Status> statusService;

    @RequestMapping({"/","home"})
    public String getHomePage() {
        return "home";
    }

    @GetMapping("/international")
    public String getInternationalPage() {
        return "redirect:/";
    }

    @RequestMapping("/defects")
    public String getAllDefects() {
        return "defects";
    }

    @RequestMapping(value = "/defect/{id}", method= RequestMethod.GET)
    public String getOrder(@PathVariable long id, Model model) {
        model.addAttribute("defect", faultService.getFaultById(id));
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("statuses", statusService.getAllStatuses());
        model.addAttribute("priorities", priorityService.getAllPriorities());
        return "defect";
    }

    @PostMapping("/saveDefect")
    public String startProgress(HttpServletRequest request) {
        Long id = Long.valueOf(request.getParameter("defectId"));
        Fault fault = faultService.getFaultById(id);
        fault.setDepartment(departmentService.getByName(request.getParameter("departmentName")));
        fault.setStatus(statusService.getByName(request.getParameter("statusName")));
        fault.setPriority(priorityService.getByName(request.getParameter("priorityName")));

        faultService.saveFault(fault);

        return "redirect:/defect/" + id;
    }


    @RequestMapping("/defectElements/{type}")
    public String showAllDepartments(@PathVariable String type, Model model) {
        model.addAttribute("type", type);
        return "/admin/defectElements";
    }

    @RequestMapping(value = "/defectElement/{id}", method= RequestMethod.GET)
    public String getDepartment(@PathVariable int id, Model model) {
        model.addAttribute("defectElement", departmentService.getById(id));
        return "/admin/defectElement";
    }

}
