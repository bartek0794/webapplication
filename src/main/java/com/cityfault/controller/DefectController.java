package com.cityfault.controller;

import com.cityfault.model.Department;
import com.cityfault.model.Fault;
import com.cityfault.model.Priority;
import com.cityfault.model.Status;
import com.cityfault.service.FaultElementService;
import com.cityfault.service.FaultService;
import com.cityfault.service.UserService;
import com.cityfault.serviceimpl.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private EmailServiceImpl emailService;

    @RequestMapping("/defects")
    public String getAllDefects() {
        return "defects";
    }

    @RequestMapping(value = "/defect/{id}", method = RequestMethod.GET)
    public String getOrder(@PathVariable long id, Model model) {
        Fault fault = faultService.getFaultById(id);
        List<Status> availableStatus = new ArrayList<Status>();

        if(fault.getStatus().getName().equals("Do akceptacji")) {
            availableStatus.add(statusService.getByName("Zaakceptowane"));
            availableStatus.add(statusService.getByName("Odrzucony"));
        } else if(fault.getStatus().getName().equals("Zaakceptowane")) {
            availableStatus.add(statusService.getByName("W realizacji"));
        } else if(fault.getStatus().getName().equals("W realizacji")) {
            availableStatus.add(statusService.getByName("Zakończony"));
        }
        availableStatus.add(fault.getStatus());

        model.addAttribute("defect", fault);
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("statuses", availableStatus);
        model.addAttribute("priorities", priorityService.getAllPriorities());
        model.addAttribute("users", userService.findByDepartment(fault.getDepartment()));
        return "defect";
    }

    @PostMapping("/saveDefect")
    public String startProgress(HttpServletRequest request) {
        Long id = Long.valueOf(request.getParameter("defectId"));
        Fault fault = faultService.getFaultById(id);
        Status status = statusService.getByName(request.getParameter("statusName"));

        String newDepartment = request.getParameter("departmentName");
        String newPriority = request.getParameter("priorityName");
        String newUser = request.getParameter("userName");
        if(newDepartment != null) {
            fault.setDepartment(departmentService.getByName(newDepartment));
        }
        if(newPriority != null) {
            fault.setPriority(priorityService.getByName(newPriority));
        }
        if(newUser != null) {
            fault.setUser(userService.findByEmail(newUser));
        }

        if (status != fault.getStatus()) {
            emailService.prepareAndSend(fault.getEmail(), fault.getDepartment().getName(), status.getName(), fault.getPriority().getName(), fault.getDescription());
        }

        fault.setStatus(status);
        faultService.saveFault(fault);

        return "redirect:/defect/" + id;
    }
}
