package com.cityfault;

import com.cityfault.service.FaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FaultRestController {
    @Autowired
    FaultService service;

    @RequestMapping(value = "/getFault/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Fault getEmployee(@PathVariable("id") Long faultId) {
        return service.getFaultById(faultId);
    }

    @RequestMapping(value = "/getAllFault", method = RequestMethod.GET)
    public @ResponseBody
    List<Fault> getEmployee() {
        return service.getAllFault();
    }

    @RequestMapping(value="/createFault/{email}/{description}", method = RequestMethod.POST)
    public ResponseEntity<Fault> creatFault(@PathVariable(value = "email") String email,
                                          @PathVariable(value = "description") String description) {
        Fault fault = new Fault(email,description);
        service.saveFault(fault);
        return new ResponseEntity<Fault>(fault, HttpStatus.CREATED);
    }
}
