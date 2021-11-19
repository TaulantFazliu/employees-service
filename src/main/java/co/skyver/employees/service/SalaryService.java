package co.skyver.employees.service;

import co.skyver.employees.model.Salary;

import java.util.List;

public interface SalaryService {

    Salary assignSalaryToEmployee(Long empNo, Salary salaryToAdd);

    List<Salary> listAllSalaries(Long empNo);
}
