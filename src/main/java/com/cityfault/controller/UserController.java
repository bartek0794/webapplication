package com.cityfault.controller;

import com.cityfault.model.Role;
import com.cityfault.model.User;
import com.cityfault.service.DepartmentService;
import com.cityfault.service.RoleService;
import com.cityfault.service.UserService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/users")
    public String showAllUsers() {
        return "/admin/users";
    }

    @RequestMapping(value = "/user/{id}", method= RequestMethod.GET)
    public String getOrder(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user";
    }

    @RequestMapping(value={"/","/index", "/login"}, method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @GetMapping("/addUser")
    public String registryForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "/admin/addUser";
    }

    @PostMapping("/addUser")
    public String userSubmit(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, HttpServletRequest request, Model model,
                             @RequestParam("file") MultipartFile file) throws IOException {

        if(!request.getParameter("confirmPassword").equals(user.getPassword())) {
            ObjectError error = new ObjectError("confirmPassword","Confirm password don't matches to password!");
            bindingResult.addError(error);
        }
        if(user.getPassword().length() < 5 || user.getPassword().length() > 15) {
            ObjectError error = new ObjectError("password","Password size must be between [5-15]");
            bindingResult.addError(error);
        }
        if(!user.getPassword().matches(".*\\d.*")) {
            ObjectError error = new ObjectError("passwordNumbers","Password must have number");
            bindingResult.addError(error);
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleService.getAllRoles());
            model.addAttribute("departments", departmentService.getAllDepartments());
            return "/admin/addUser";
        }

        if(file.getOriginalFilename().equals("")) {
            File defaultFile = new File("C:\\Users\\Bartek\\IdeaProjects\\ordersystemmanagement\\src\\main\\resources\\static\\img\\avatar.jpg");
            FileInputStream input = new FileInputStream(defaultFile);
            file = new MockMultipartFile("defaultFile",
                    defaultFile.getName(), "text/plain", IOUtils.toByteArray(input));
        }

        byte[] bytes = new byte[0];
        try {
            bytes = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        user.setAvatar(bytes);

        Role role = roleService.getRoleByName(request.getParameter("role"));
        user.setDepartment(departmentService.getDepartmentByName(request.getParameter("departmentName")));
        userService.save(user, new HashSet<Role>(Arrays.asList(role)));
        return "redirect:users";
    }
}
