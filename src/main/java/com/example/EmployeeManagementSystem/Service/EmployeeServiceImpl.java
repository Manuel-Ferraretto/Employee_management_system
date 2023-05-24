package com.example.EmployeeManagementSystem.Service;

import com.example.EmployeeManagementSystem.Entities.Employee;
import com.example.EmployeeManagementSystem.Repository.EmployeeRepository;
import jakarta.persistence.TypedQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;
    private final SessionFactory sessionFactory;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, SessionFactory sessionFactory) {

        this.employeeRepository = employeeRepository;
        this.sessionFactory = sessionFactory;
    }

    public void addNewEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(Long idEmployee) {
        employeeRepository.deleteById(idEmployee);
    }

    @Override
    @Transactional
    public Optional<Employee> getById(Long idEmployee) {
        return employeeRepository.findById(idEmployee);
    }

    @Override
    public List<Employee> findAvailableEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (Session session = sessionFactory.openSession()){
            String hql = "SELECT e.idEmployee, e.name, e.lastName " +
                    "FROM Employee e " +
                    "WHERE e.idEmployee NOT IN (" +
                    "    SELECT emp.idEmployee " +
                    "    FROM Project p " +
                    "    INNER JOIN p.employeesList emp " +
                    "    WHERE p.finishDate IS NULL" +
                    ")";
            TypedQuery<Object[]> query = session.createQuery(hql, Object[].class);
            List<Object[]> results = query.getResultList();


            for (Object[] row : results) {
                Long idEmployee = (Long) row[0];
                String name = (String) row[1];
                String lastName = (String) row[2];

                Employee employee = new Employee();
                employee.setIdEmployee(idEmployee);
                employee.setName(name);
                employee.setLastName(lastName);

                employees.add(employee);
            }
        }
        catch (HibernateException ex){
            ex.printStackTrace();
        }
        return employees;
    }


}
