package com.example.EmployeeManagementSystem.Entities;


import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee")
    private Long idEmployee;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "salary")
    private int salary;

    @ManyToMany()
    @JoinTable(
            name="employee_skill",
            joinColumns = @JoinColumn(name = "id_employee", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_skill", nullable = false)
    )
    private List<Skill> skills;

    @ManyToMany(mappedBy = "employeesList")
    private Set<Project> projectsList;

    public Employee() {
    }

    public Employee(Long idEmployee, String name, String lastName, String email, String password, LocalDate dateOfBirth, int salary) {
        this.idEmployee = idEmployee;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
    }

    public Employee(String name, String lastName, String email, LocalDate dateOfBirth, int salary) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
    }

    public void AddSkill(Skill skill){
        if (this.skills == null){
            this.skills = new ArrayList<>();
        }
        this.skills.add(skill);
    }

    /*
    public void removeSkill(Skill skill){
        skills.remove(skill);
        skill.getEmployees().remove(this);
    } */


    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {return lastName; }

    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public LocalDate getDateOfBirth() {return dateOfBirth;}

    public void setDateOfBirth(LocalDate dateOfBirth) {this.dateOfBirth = dateOfBirth;}

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Set<Project> getProjects() {
        return projectsList;
    }

    public void setProjects(Set<Project> projects) {
        this.projectsList = projects;
    }

    @Override
    public String toString() {
        return "Employee{" +
                ", name='" + name + '\'' +
                ", last name='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                '}';
    }
}
