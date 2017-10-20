package com.cityfault.repository;

import com.cityfault.Fault;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaultRepository extends JpaRepository<Fault, Long> {
    Fault findByFaultId(Long id);

}
