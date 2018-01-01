package com.cityfault.repository;

import com.cityfault.model.Defect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaultRepository extends JpaRepository<Defect, Long> {
    Defect findByFaultId(Long id);
}
