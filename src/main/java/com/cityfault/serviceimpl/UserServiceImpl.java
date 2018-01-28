package com.cityfault.serviceimpl;

import com.cityfault.model.Department;
import com.cityfault.model.Role;
import com.cityfault.model.User;
import com.cityfault.repository.UserRepository;
import com.cityfault.service.RoleService;
import com.cityfault.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RoleService roleService;

    @Override
    public void save(User user, HashSet<Role> role) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        user.setRoles(role);
        userRepository.save(user);
    }

    @Override
    public void update(User user, HashSet<Role> role) {
        User oldUser = userRepository.findByUserId(user.getUserId());
        user.setRoles(role);
        user.setActive(1);
        user.setPassword(oldUser.getPassword());
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String username) {
        return userRepository.findByEmail(username);
    }
    @Override
    public User findById(int id) {
        return userRepository.findByUserId(id);
    }
    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }
    @Override
    public List<User> findByDepartment(Department department) {
       List<User> userList = new ArrayList<User>();

       for(User user : userRepository.findByDepartment(department)) {
           if(user.getRoles().contains(roleService.getRoleByName("EMPLOYEE"))) {
               userList.add(user);
           }
       }
        return userList;
    }
}
