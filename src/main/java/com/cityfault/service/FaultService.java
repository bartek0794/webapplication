package com.cityfault.service;

import com.cityfault.model.Defect;

import java.util.List;

public interface FaultService {
    void saveFault(Defect defect);
    Defect getFaultById(Long id);
    List<Defect> getAllFault();
}
