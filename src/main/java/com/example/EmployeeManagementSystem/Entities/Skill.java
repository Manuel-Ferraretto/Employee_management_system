package com.example.EmployeeManagementSystem.Entities;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "skill")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_skill")
    private Long idSkill;

    @Column(name="name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "skills")
    private List<Employee> employees;

    public Long getIdSkill() {
        return idSkill;
    }

    public void setIdSkill(Long idSkill) {
        this.idSkill = idSkill;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Skill(Long idSkill, String name) {
        this.idSkill = idSkill;
        this.name = name;
    }

    public Skill(){

    }


}