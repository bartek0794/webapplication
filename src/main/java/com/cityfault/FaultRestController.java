package com.cityfault;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FaultRestController {

    @RequestMapping(value = "/getFault/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Fault getEmployee(@PathVariable("id") int faultId) {

        return new Fault("bartek0794@gmail.com","Dziura w jezdni");
    }

    @RequestMapping(value="/createFault/{email}/{description}", method = RequestMethod.POST)
    public ResponseEntity<Fault> creatFault(@PathVariable(value = "email") String email,
                                          @PathVariable(value = "description") String description) {
        Fault fault = new Fault(email,description);
        System.out.println(fault.getEmail());
        return new ResponseEntity<Fault>(fault, HttpStatus.CREATED);
    }
}
