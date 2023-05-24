package com.example.EmployeeManagementSystem.Service;

import com.example.EmployeeManagementSystem.Entities.Employee;
import com.example.EmployeeManagementSystem.Entities.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    void addNewEmployee(Project project);

    List<Project> findAll();

    List<Project> findFinishedProjects();

    List<Project> findInProgressProjects();

    void updateProject(Project project);

    void deleteEmployeeById(Long idProject);

    Optional<Project> findById(Long idProject);

    void addCurrentEmployeeToProject(Project project, Employee employee);


}
