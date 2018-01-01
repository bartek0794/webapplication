package com.cityfault.repository;

import com.cityfault.model.Fault;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaultRepository extends JpaRepository<Fault, Long> {
    Fault findByFaultId(Long id);
}
