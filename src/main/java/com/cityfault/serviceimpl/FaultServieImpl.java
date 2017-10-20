package com.cityfault.serviceimpl;


import com.cityfault.Fault;
import com.cityfault.repository.FaultRepository;
import com.cityfault.service.FaultService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FaultServieImpl implements FaultService {
    @Autowired
    FaultRepository repository;

    @Override
    public void saveFault(Fault fault) {
        repository.save(fault);
    }

    @Override
    public Fault getFaultById(Long id) {
        return repository.findByFaultId(id);
    }

    @Override
    public List<Fault> getAllFault() {
        return repository.findAll();
    }
}
