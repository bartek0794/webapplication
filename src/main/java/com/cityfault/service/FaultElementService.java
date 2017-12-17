package com.cityfault.service;

import com.cityfault.model.FaultElement;

import java.util.List;

public interface FaultElementService <T extends FaultElement> {
    void saveFaultElement(T entity);
    T getById(int id);
    T getByName(String name);
    List<T> getAll();
}
