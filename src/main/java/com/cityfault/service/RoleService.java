package com.cityfault.service;

import com.cityfault.model.Role;

import java.util.List;

public interface RoleService {
    Role getRoleByName(String name);
    Role getRoleById(int id);
    List<Role> getAllRoles();
}
