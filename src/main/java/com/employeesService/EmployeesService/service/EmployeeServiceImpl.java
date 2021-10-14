package com.employeesService.EmployeesService.service;

import com.employeesService.EmployeesService.exception.EmployeeNotFoundException;
import com.employeesService.EmployeesService.model.Employee;
import com.employeesService.EmployeesService.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(Long empNo) {
        Employee employee = employeeRepository.findById(empNo).orElseThrow(() -> new EmployeeNotFoundException(empNo));
        return employee;
    }

    @Override
    public Employee update(Long empNo, Employee employee) {
        Employee foundEmployee = this.employeeRepository.findById(empNo).orElseThrow(() -> new EmployeeNotFoundException(empNo));
        foundEmployee.setFirstName(employee.getFirstName());
        foundEmployee.setLastName(employee.getLastName());
        foundEmployee.setBirthDate(employee.getBirthDate());
        foundEmployee.setHireDate(employee.getHireDate());
        foundEmployee.setGender(employee.getGender());
        return this.employeeRepository.save(foundEmployee);
    }

    @Override
    public void delete(Long empNo) {
        employeeRepository.deleteById(empNo);
    }
}
