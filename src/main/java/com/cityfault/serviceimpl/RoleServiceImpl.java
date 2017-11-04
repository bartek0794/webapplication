package com.cityfault.serviceimpl;

import com.cityfault.model.Role;
import com.cityfault.repository.RoleRepository;
import com.cityfault.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository repository;

    @Override
    public Role getRoleByName(String name) {
        return repository.findByRole(name);
    }

    @Override
    public Role getRoleById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<Role> getAllRoles() {
        return repository.findAll();
    }
}