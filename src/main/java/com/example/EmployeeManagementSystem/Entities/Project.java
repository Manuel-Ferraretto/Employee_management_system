package com.example.EmployeeManagementSystem.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_project")
    private Long idProject;

    @Column(name="title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "finish_date")
    private LocalDate finishDate;

    @ManyToMany()
    @JoinTable(
            name = "employees_projects",
            joinColumns = @JoinColumn(name = "id_project", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_employee", nullable = false))
    private Set<Employee> employeesList;

    public Project(Long idProject, String title, String description, LocalDate startDate) {
        this.idProject = idProject;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.finishDate = null;
    }

    public Project(){}

    public Long getIdProject() {
        return idProject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public Set<Employee> getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(Set<Employee> employeesList) {
        this.employeesList = employeesList;
    }

    @Override
    public String toString() {
        return "Project{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                '}';
    }

}
