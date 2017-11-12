package com.cityfault.restcontroller;

import com.cityfault.model.Department;
import com.cityfault.model.Fault;
import com.cityfault.model.Status;
import com.cityfault.service.DepartmentService;
import com.cityfault.service.FaultService;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FaultRestController {

    @Autowired
    private FaultService faultService;
    @Autowired
    private DepartmentService departmentService;

    //FAULTS
    @RequestMapping(value = "/getFault/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Fault getFault(@PathVariable("id") Long faultId) {
        return faultService.getFaultById(faultId);
    }

    @RequestMapping(value = "/getAllFault", method = RequestMethod.GET)
    public @ResponseBody
    List<Fault> getAllFault() {
        return faultService.getAllFault();
    }

    @RequestMapping(value="/createFault", method = RequestMethod.POST)
    public ResponseEntity<Fault> createFault(@RequestBody Fault fault) {
        fault.setDepartment(departmentService.getDepartmentById(fault.getDepartment().getDepartmentId()));
        fault.setStatus(Status.TO_VERIFICATION.toString());
        fault.setCreateDate(LocalDateTime.now());
        faultService.saveFault(fault);
        return new ResponseEntity<Fault>(fault, HttpStatus.CREATED);
    }

    //DEPARTMNENTS
    @RequestMapping(value = "/getAllDepartments", method = RequestMethod.GET)
    public @ResponseBody
    List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

}
