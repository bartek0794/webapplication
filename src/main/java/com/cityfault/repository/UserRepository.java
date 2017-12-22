package com.cityfault.repository;

import com.cityfault.model.Department;
import com.cityfault.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUserId(int id);
    List<User> findByDepartment(Department department);
}