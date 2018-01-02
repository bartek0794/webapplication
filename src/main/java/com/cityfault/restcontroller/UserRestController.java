package com.cityfault.restcontroller;

import com.cityfault.model.User;
import com.cityfault.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserRestController {
    @Autowired
    private UserService userService;

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
}
