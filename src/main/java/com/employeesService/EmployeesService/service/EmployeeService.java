package com.employeesService.EmployeesService.service;

import com.employeesService.EmployeesService.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee save(Employee employee);

    List<Employee> getAll();

    Employee getById(Long empNo);

    Employee update(Long empNo, Employee employee);

    void delete(Long empNo);
}
