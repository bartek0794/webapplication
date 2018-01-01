package com.cityfault.restcontroller;

import com.cityfault.model.*;
import com.cityfault.service.FaultElementService;
import com.cityfault.service.FaultService;
import com.cityfault.service.PhotoService;
import com.cityfault.service.UserService;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FaultRestController {

    @Autowired
    private FaultService faultService;
    @Autowired
    private UserService userService;
    @Autowired
    private FaultElementService<Department> departmentService;
    @Autowired
    private FaultElementService<Status> statusService;
    @Autowired
    private FaultElementService<Priority> priorityService;
    @Autowired
    private PhotoService photoService;


    //FAULTS
    @RequestMapping(value = "/getFault/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Fault getFault(@PathVariable("id") Long faultId) {
        return faultService.getFaultById(faultId);
    }

    @RequestMapping(value = "/getAllFault", method = RequestMethod.GET)
    public @ResponseBody
    List<Fault> getAllFault() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = userService.findByEmail(auth.getName());

        List<Fault> defects = new ArrayList<Fault>();
        for(Fault defect: faultService.getAllFault()) {
            defect.setPhoto(new Photo());
            defect.setUser(null);
            if(auth.getAuthorities().toString().equals("[DEPARTMENT_ADMIN]")) {
                if(defect.getDepartment().getId() == loggedUser.getDepartment().getId()) {
                    defects.add(defect);
                }
            } else if(auth.getAuthorities().toString().equals("[EMPLOYEE]")) {
                if(defect.getUser().getUserId() == loggedUser.getUserId()) {
                    defects.add(defect);
                }
            } else {
                defects.add(defect);
            }
        }
        return defects;
    }

    @RequestMapping(value="/createFault", method = RequestMethod.POST)
    public ResponseEntity<Fault> createFault(@RequestBody Fault fault) {
        fault.setDepartment(departmentService.getById(fault.getDepartment().getId()));
        fault.setStatus(statusService.getByName("Do akceptacji"));
        fault.setPriority(priorityService.getByName("Niski"));
        fault.setCreateDate(LocalDateTime.now());
        photoService.savePhoto(fault.getPhoto());
        faultService.saveFault(fault);
        return new ResponseEntity<Fault>(fault, HttpStatus.CREATED);
    }

    //Departments
    @RequestMapping(value = "/getAllDepartments", method = RequestMethod.GET)
    public @ResponseBody
    List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    //Statuses
    @RequestMapping(value = "/getAllStatuses", method = RequestMethod.GET)
    public @ResponseBody
    List<Status> getAllStatuses() {
        return statusService.getAllStatuses();
    }

    //Priorities
    @RequestMapping(value = "/getAllPriorities", method = RequestMethod.GET)
    public @ResponseBody
    List<Priority> getAllPriorities() {
        return priorityService.getAllPriorities();
    }

}
