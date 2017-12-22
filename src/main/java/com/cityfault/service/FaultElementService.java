package com.cityfault.service;

import com.cityfault.model.Department;
import com.cityfault.model.FaultElement;
import com.cityfault.model.Priority;
import com.cityfault.model.Status;

import java.util.List;

public interface FaultElementService <T extends FaultElement> {
    void saveFaultElement(T entity);
    T getById(int id);
    T getByName(String name);
    List<T> getAll();
    List<Department> getAllDepartments();
    List<Status> getAllStatuses();
    List<Priority> getAllPriorities();
}
