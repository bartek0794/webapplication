package com.cityfault.serviceimpl;

import com.cityfault.model.FaultElement;
import com.cityfault.repository.FaultElementRepository;
import com.cityfault.service.FaultElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
