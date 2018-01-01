package com.cityfault.serviceimpl;


import com.cityfault.model.Defect;
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
    public void saveFault(Defect defect) {
        repository.save(defect);
    }

    @Override
    public Defect getFaultById(Long id) {
        return repository.findByFaultId(id);
    }

    @Override
    public List<Defect> getAllFault() {
        return repository.findAll();
    }
}
