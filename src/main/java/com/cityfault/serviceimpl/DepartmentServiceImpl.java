package com.cityfault.serviceimpl;

import com.cityfault.model.Department;
import com.cityfault.repository.DepartmentRepository;
import com.cityfault.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository repository;

    public void saveDepartment(Department department) {
        repository.save(department);
    }
    public Department getDepartmentById(int id) {
        return repository.findByDepartmentId(id);
    }
    public Department getDepartmentByName(String name) {
        return repository.findByDepartmentName(name);
    }
    public List<Department> getAllDepartments() {
        return repository.findAll();
    }
}
