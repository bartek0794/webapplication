package com.cityfault.service;

import com.cityfault.Fault;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FaultService {
    void saveFault(Fault fault);
    Fault getFaultById(Long id);
    List<Fault> getAllFault();
}
