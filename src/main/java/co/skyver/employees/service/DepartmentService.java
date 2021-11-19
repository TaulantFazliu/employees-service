package co.skyver.employees.service;

import co.skyver.employees.model.Department;

import java.util.List;

public interface DepartmentService {

    Department save(Department department);

    List<Department> getAll();

    Department getById(String deptNo);

    Department update(String deptNo, Department department);

    void delete(String deptNo);
}
