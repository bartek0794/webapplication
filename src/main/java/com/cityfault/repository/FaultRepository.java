package com.cityfault.repository;

import com.cityfault.Fault;
import org.springframework.stereotype.Repository;

@Repository
public class FaultRepository extends JpaRepository<Fault, Long>{

}
