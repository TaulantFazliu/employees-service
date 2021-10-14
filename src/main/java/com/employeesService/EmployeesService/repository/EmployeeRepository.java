package com.employeesService.EmployeesService.repository;

import com.employeesService.EmployeesService.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    Employee save(Employee employee);

    List<Employee> findAll();

    Optional<Employee> findById(Long emptNo);

    void deleteById(Long empNo);
}
