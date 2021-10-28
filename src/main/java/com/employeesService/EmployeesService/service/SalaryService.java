package com.employeesService.EmployeesService.service;

import com.employeesService.EmployeesService.model.Salary;

import java.util.List;

public interface SalaryService {

    Salary assignSalaryToEmployee(Long empNo, Salary salaryToAdd);

    List<Salary> listAllSalaries(Long empNo);
}
