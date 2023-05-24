package com.example.EmployeeManagementSystem.Repository;

import com.example.EmployeeManagementSystem.Entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
