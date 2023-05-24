package com.example.EmployeeManagementSystem.Repository;


import com.example.EmployeeManagementSystem.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
