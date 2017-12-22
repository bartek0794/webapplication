package com.cityfault.controller;

import com.cityfault.model.Department;
import com.cityfault.model.Fault;
import com.cityfault.model.Priority;
import com.cityfault.model.Status;
import com.cityfault.service.FaultElementService;
import com.cityfault.service.FaultService;
import com.cityfault.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DefectController {

    @Autowired
    private FaultService faultService;
    @Autowired
    private UserService userService;
    @Autowired
    private FaultElementService<Department> departmentService;
    @Autowired
    private FaultElementService<Priority> priorityService;
    @Autowired
    private FaultElementService<Status> statusService;

    @RequestMapping("/defects")
    public String getAllDefects() {
        return "defects";
    }

    @RequestMapping(value = "/defect/{id}", method= RequestMethod.GET)
    public String getOrder(@PathVariable long id, Model model) {
        Fault fault = faultService.getFaultById(id);
        model.addAttribute("defect", fault);
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("statuses", statusService.getAllStatuses());
        model.addAttribute("priorities", priorityService.getAllPriorities());
        model.addAttribute("users", userService.findByDepartment(fault.getDepartment()));
        return "defect";
    }

    @PostMapping("/saveDefect")
    public String startProgress(HttpServletRequest request) {
        Long id = Long.valueOf(request.getParameter("defectId"));
        Fault fault = faultService.getFaultById(id);
        fault.setDepartment(departmentService.getByName(request.getParameter("departmentName")));
        fault.setStatus(statusService.getByName(request.getParameter("statusName")));
        fault.setPriority(priorityService.getByName(request.getParameter("priorityName")));
        fault.setUser(userService.findByEmail(request.getParameter("userName")));
        faultService.saveFault(fault);

        return "redirect:/defect/" + id;
    }
}
