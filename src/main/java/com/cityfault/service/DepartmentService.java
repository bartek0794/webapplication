package com.cityfault.service;

import com.cityfault.model.Department;

import java.util.List;

public interface DepartmentService {
    void saveDepartment(Department department);
    Department getDepartmentById(int id);
    List<Department> getAllDepartments();
}
