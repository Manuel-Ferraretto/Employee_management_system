package com.example.EmployeeManagementSystem.Service;

import com.example.EmployeeManagementSystem.Entities.Employee;
import com.example.EmployeeManagementSystem.Entities.Project;
import com.example.EmployeeManagementSystem.Repository.EmployeeRepository;
import com.example.EmployeeManagementSystem.Repository.ProjectRepository;
import jakarta.persistence.TypedQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;
    private final SessionFactory sessionFactory;

    public ProjectServiceImpl(ProjectRepository projectRepository, EmployeeRepository employeeRepository, SessionFactory sessionFactory) {

        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
        this.sessionFactory = sessionFactory;
    }



    @Override
    public void addNewEmployee(Project project) {
        projectRepository.save(project);
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public List<Project> findFinishedProjects() {
        List<Project> finishedProjects = null;
        try (Session session = sessionFactory.openSession()){
            String hql = "FROM Project p WHERE p.finishDate IS NOT NULL";
            TypedQuery<Project> query = session.createQuery(hql, Project.class);
            finishedProjects = query.getResultList();
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        return finishedProjects;
    }

    @Override
    public List<Project> findInProgressProjects() {
        List<Project> inProgressProjects = null;
        try (Session session = sessionFactory.openSession()){
            String hql = "FROM Project p WHERE p.finishDate IS NULL";
            TypedQuery<Project> query = session.createQuery(hql, Project.class);
            inProgressProjects = query.getResultList();
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        return inProgressProjects;
    }

    @Override
    public void updateProject(Project project) {
        projectRepository.save(project);
    }

    @Override
    public void deleteEmployeeById(Long idProject) {
        projectRepository.deleteById(idProject);
    }

    @Override
    @Transactional
    public Optional<Project> findById(Long idProject) {
        return projectRepository.findById(idProject);
    }

    @Override
    @Transactional
    public void addCurrentEmployeeToProject(Project project, Employee employee) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();

            project.getEmployeesList().add(employee);
            employee.getProjects().add(project);
            session.merge(project);

            session.getTransaction().commit();
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
    }
}
