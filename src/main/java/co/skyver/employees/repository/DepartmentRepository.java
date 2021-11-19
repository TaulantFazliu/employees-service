package co.skyver.employees.repository;

import co.skyver.employees.model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository {

    Department save(Department department);

    List<Department> findAll();

    Optional<Department> findById(String deptNo);

    Optional<Department> findByIdWithEmployees(String deptNo);

    void deleteById(String deptNo);
}
