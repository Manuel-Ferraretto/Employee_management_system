package com.example.EmployeeManagementSystem.Service;

import com.example.EmployeeManagementSystem.Entities.Employee;

import java.util.List;
import java.util.Optional;


public interface EmployeeService {
    void addNewEmployee(Employee employee);

    List<Employee> findAll();

    void updateEmployee(Employee employee);

    void deleteEmployeeById(Long idEmployee);

    Optional<Employee> getById(Long idEmployee);

    List<Employee> findAvailableEmployees();
}
