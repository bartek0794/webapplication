package com.cityfault.repository;

import com.cityfault.model.FaultElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FaultElementRepository <T extends FaultElement> extends JpaRepository<T, Long> {
    T findById(int id);
    T findByName(String name);
}
