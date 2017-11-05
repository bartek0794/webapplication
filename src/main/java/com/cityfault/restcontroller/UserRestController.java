package com.cityfault.restcontroller;

import com.cityfault.model.User;
import com.cityfault.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRestController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    public @ResponseBody
    List<User> getAllUsers() {
        return userService.findAll();
    }
}
