package com.cityfault.service;

import com.cityfault.model.Fault;
import java.util.List;

public interface FaultService {
    void saveFault(Fault fault);
    Fault getFaultById(Long id);
    List<Fault> getAllFault();
}
