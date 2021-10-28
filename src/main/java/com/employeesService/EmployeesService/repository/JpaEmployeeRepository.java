package com.employeesService.EmployeesService.repository;

import com.employeesService.EmployeesService.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaEmployeeRepository implements EmployeeRepository {

    private final EntityManager entityManager;

    @Autowired
    public JpaEmployeeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        this.entityManager.persist(employee);
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        return this.entityManager.createQuery("select e from employees e", Employee.class).getResultList();
    }

    @Override
    public Optional<Employee> findById(Long emptNo) {
        try {
            return Optional.ofNullable(this.entityManager.find(Employee.class, emptNo));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Employee> findByIdWithSalaries(Long empNo) {
        try {
            TypedQuery<Employee> query = this.entityManager.createQuery("SELECT e FROM employees e JOIN FETCH e.salaries WHERE e.empNo = :empNo", Employee.class).setParameter("empNo", empNo);
            return Optional.ofNullable(query.getSingleResult());
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public void deleteById(Long empNo) {
        Employee employee = this.entityManager.find(Employee.class, empNo);
        this.entityManager.remove(employee);
    }

}
