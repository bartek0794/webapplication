package com.cityfault.serviceimpl;

import com.cityfault.model.Department;
import com.cityfault.model.FaultElement;
import com.cityfault.model.Priority;
import com.cityfault.model.Status;
import com.cityfault.repository.FaultElementRepository;
import com.cityfault.service.FaultElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FaultElementServiceImpl <T extends FaultElement> implements FaultElementService <T>{
    @Autowired
    private FaultElementRepository<T> repository;

    public void saveFaultElement(T entity) {
        repository.save(entity);
    }
    public T getById(int id) {
        return repository.findById(id);
    }
    public T getByName(String name) {
        return repository.findByName(name);
    }
    public List<T> getAll() {
        return repository.findAll();
    }

    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<Department>();
        for(FaultElement department : repository.findAll()) {
            if(department instanceof Department) {
                departments.add((Department) department);
            }
        }
        return departments;
    }

    public List<Status> getAllStatuses() {
        List<Status> statuses = new ArrayList<Status>();
        for(FaultElement status :repository.findAll()) {
            if(status instanceof Status) {
                statuses.add((Status) status);
            }
        }
        return statuses;
    }

    public List<Priority> getAllPriorities() {
        List<Priority> priorities = new ArrayList<Priority>();
        for(FaultElement priority : repository.findAll()) {
            if(priority instanceof Priority) {
                priorities.add((Priority) priority);
            }
        }
        return priorities;
    }
}
