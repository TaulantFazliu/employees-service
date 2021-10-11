package com.employeesService.EmployeesService.repository;

import com.employeesService.EmployeesService.model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository {

    Department save(Department department);

    List<Department> findAll();

    Optional<Department> findById(String deptNo);

    Department update(Department department);

    void deleteById(String deptNo);

}
