package com.cityfault.controller;

import com.cityfault.model.Defect;
import com.cityfault.model.Department;
import com.cityfault.model.Priority;
import com.cityfault.model.Status;
import com.cityfault.service.FaultElementService;
import com.cityfault.service.FaultService;
import com.cityfault.service.UserService;
import com.cityfault.serviceimpl.EmailServiceImpl;
import org.joda.time.LocalDateTime;
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
        Defect defect = faultService.getFaultById(id);
        List<Status> availableStatus = new ArrayList<Status>();

        if(defect.getStatus().getName().equals("Do akceptacji")) {
            availableStatus.add(statusService.getByName("Zaakceptowane"));
            availableStatus.add(statusService.getByName("Odrzucony"));
        } else if(defect.getStatus().getName().equals("Zaakceptowane")) {
            availableStatus.add(statusService.getByName("W realizacji"));
        } else if(defect.getStatus().getName().equals("W realizacji")) {
            availableStatus.add(statusService.getByName("Zakończony"));
        }
        availableStatus.add(defect.getStatus());

        model.addAttribute("defect", defect);
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("statuses", availableStatus);
        model.addAttribute("priorities", priorityService.getAllPriorities());
        model.addAttribute("users", userService.findByDepartment(defect.getDepartment()));
        return "defect";
    }

    @PostMapping("/saveDefect")
    public String startProgress(HttpServletRequest request) {
        Long id = Long.valueOf(request.getParameter("defectId"));
        Defect defect = faultService.getFaultById(id);
        Status status = statusService.getByName(request.getParameter("statusName"));

        String newDepartment = request.getParameter("departmentName");
        String newPriority = request.getParameter("priorityName");
        String newUser = request.getParameter("userName");
        String resolveDescription = request.getParameter("resolveDescription");

        if(newDepartment != null) {
            defect.setDepartment(departmentService.getByName(newDepartment));
        }
        if(newPriority != null) {
            defect.setPriority(priorityService.getByName(newPriority));
        }
        if(newUser != null) {
            defect.setUser(userService.findByEmail(newUser));
        }
        if(resolveDescription != null) {
            defect.setResolveDescription(resolveDescription);
            defect.setResolveDate(LocalDateTime.now());
        }

        if (status != defect.getStatus()) {
            emailService.prepareAndSend(defect.getEmail(), defect.getDepartment().getName(), status.getName(), defect.getPriority().getName(), defect.getDescription());
        }

        defect.setStatus(status);
        faultService.saveFault(defect);

        return "redirect:/defect/" + id;
    }
}
