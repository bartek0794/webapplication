package com.cityfault.restcontroller;

import com.cityfault.model.*;
import com.cityfault.service.FaultElementService;
import com.cityfault.service.FaultService;
import com.cityfault.service.PhotoService;
import com.cityfault.service.UserService;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
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
            if(auth.getAuthorities().toString().equals("[DEPARTMENT_ADMIN]")) {
                if(defect.getDepartment().getId() == loggedUser.getDepartment().getId()) {
                    defect.setUser(null);
                    defects.add(defect);
                }
            } else if(auth.getAuthorities().toString().equals("[EMPLOYEE]")) {
                if(defect.getUser() != null) {
                    if (defect.getUser().getUserId() == loggedUser.getUserId()) {
                        defect.setUser(null);
                        defects.add(defect);
                    }
                }
            } else {
                defect.setUser(null);
                defects.add(defect);
            }
        }
        return defects;
    }

    @RequestMapping(value="/createFault", method = RequestMethod.POST)
    public ResponseEntity<Fault> createFault(@RequestBody Fault fault) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd-MM-yyyy, HH:mm");

        fault.setDepartment(departmentService.getById(fault.getDepartment().getId()));
        fault.setStatus(statusService.getByName("Do akceptacji"));
        fault.setPriority(priorityService.getByName("Niski"));
        fault.setCreateDate(LocalDateTime.now().toString(fmt));
        photoService.savePhoto(fault.getPhoto());
        faultService.saveFault(fault);
        return new ResponseEntity<Fault>(fault, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    public @ResponseBody
    List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();

        for(User user: userService.findAll()) {
            user.setAvatar(new byte[0]);
            user.setPassword("");
            users.add(user);
        }
        return users;
    }

    @RequestMapping(value = "/getAllDepartments", method = RequestMethod.GET)
    public @ResponseBody
    List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @RequestMapping(value = "/getAllStatuses", method = RequestMethod.GET)
    public @ResponseBody
    List<Status> getAllStatuses() {
        return statusService.getAllStatuses();
    }

    @RequestMapping(value = "/getAllPriorities", method = RequestMethod.GET)
    public @ResponseBody
    List<Priority> getAllPriorities() {
        return priorityService.getAllPriorities();
    }

}
