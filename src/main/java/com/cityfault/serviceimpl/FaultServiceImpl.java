package com.cityfault.serviceimpl;


import com.cityfault.model.Fault;
import com.cityfault.repository.FaultRepository;
import com.cityfault.service.FaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaultServiceImpl implements FaultService {
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
