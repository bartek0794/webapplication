package com.cityfault.service;

import com.cityfault.model.Department;
import com.cityfault.model.Role;
import com.cityfault.model.User;

import java.util.HashSet;
import java.util.List;

public interface UserService {
    User findByEmail(String email);
    void save(User user, HashSet<Role> role);
    void update(User user, HashSet<Role> role);
    User findById(int id);
    List<User> findAll();
    List<User> findByDepartment(Department department);
}
