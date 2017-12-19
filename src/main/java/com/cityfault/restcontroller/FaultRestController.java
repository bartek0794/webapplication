package com.cityfault.restcontroller;

import com.cityfault.model.*;
import com.cityfault.service.FaultElementService;
import com.cityfault.service.FaultService;
import com.cityfault.service.PhotoService;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FaultRestController {

    @Autowired
    private FaultService faultService;
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
        List<Fault> defects = new ArrayList<Fault>();
        for(Fault defect: faultService.getAllFault()) {
            defect.setPhoto(null);
            defects.add(defect);
        }
        return defects;
    }

    @RequestMapping(value="/createFault", method = RequestMethod.POST)
    public ResponseEntity<Fault> createFault(@RequestBody Fault fault) {
        fault.setDepartment(departmentService.getById(fault.getDepartment().getId()));
        fault.setStatus(statusService.getByName("Do akceptacji"));
        fault.setCreateDate(LocalDateTime.now());
        photoService.savePhoto(fault.getPhoto());
        faultService.saveFault(fault);
        return new ResponseEntity<Fault>(fault, HttpStatus.CREATED);
    }

    //Departments
    @RequestMapping(value = "/getAllDepartments", method = RequestMethod.GET)
    public @ResponseBody
    List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<Department>();
        for(FaultElement department : departmentService.getAll()) {
            if(department instanceof Department) {
                departments.add((Department) department);
            }
        }
        return departments;
    }

    //Statuses
    @RequestMapping(value = "/getAllStatuses", method = RequestMethod.GET)
    public @ResponseBody
    List<Status> getAllStatuses() {
        List<Status> statuses = new ArrayList<Status>();
        for(FaultElement status :statusService.getAll()) {
            if(status instanceof Status) {
                statuses.add((Status) status);
            }
        }
        return statuses;
    }

    //Statuses
    @RequestMapping(value = "/getAllPriorities", method = RequestMethod.GET)
    public @ResponseBody
    List<Priority> getAllPriorities() {
        List<Priority> priorities = new ArrayList<Priority>();
        for(FaultElement priority : priorityService.getAll()) {
            if(priority instanceof Priority) {
                priorities.add((Priority) priority);
            }
        }
        return priorities;
    }

}
