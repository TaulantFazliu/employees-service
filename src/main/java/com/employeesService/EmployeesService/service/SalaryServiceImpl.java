package com.employeesService.EmployeesService.service;

import com.employeesService.EmployeesService.exception.EmployeeNotFoundException;
import com.employeesService.EmployeesService.model.Employee;
import com.employeesService.EmployeesService.model.Salary;
import com.employeesService.EmployeesService.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SalaryServiceImpl implements SalaryService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public SalaryServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    @Transactional
    public Salary assignSalaryToEmployee(Long empNo, Salary salaryToAdd) {
        Employee employee = this.employeeRepository.findById(empNo).orElseThrow(() -> new EmployeeNotFoundException(empNo));
        salaryToAdd.setEmpNo(empNo);
        List<Salary> salaries = employee.getSalaries();
        for (Salary salary : salaries) {
            if (salary.getToDate() == null) {
                salary.setToDate(salaryToAdd.getFromDate());
            }
        }
        employee.assignSalary(salaryToAdd);
        this.employeeRepository.save(employee);
        return salaryToAdd;
    }

    @Override
    public List<Salary> listAllSalaries(Long empNo) {
        Employee employee = this.employeeRepository.findByIdWithSalaries(empNo).orElseThrow(() -> new EmployeeNotFoundException(empNo));
        return employee.getSalaries();
    }
}
