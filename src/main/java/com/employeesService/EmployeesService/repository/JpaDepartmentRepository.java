package com.employeesService.EmployeesService.repository;

import com.employeesService.EmployeesService.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaDepartmentRepository implements DepartmentRepository {

    private final EntityManager entityManager;

    @Autowired
    public JpaDepartmentRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Department save(Department department) {
        this.entityManager.persist(department);
        return department;
    }

    @Override
    public List<Department> findAll() {
        return this.entityManager.createQuery("SELECT d FROM Department d ", Department.class).getResultList();
    }

    @Override
    public Optional<Department> findById(String deptNo) {
        return Optional.ofNullable(this.entityManager.find(Department.class, deptNo));
    }

    @Override
    public Optional<Department> findByIdWithEmployees(String deptNo) {
        TypedQuery<Department> query = this.entityManager.createQuery("SELECT d  FROM Department d JOIN FETCH d.employees e join fetch e.employee s WHERE d.deptNo = :deptNo AND e.toDate IS NULL", Department.class).setParameter("deptNo", deptNo);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    @Transactional
    public void deleteById(String deptNo) {
        Department department = this.entityManager.find(Department.class, deptNo);
        this.entityManager.remove(department);
    }
}
