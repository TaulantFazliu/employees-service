package com.employeesService.EmployeesService.controller;

import com.employeesService.EmployeesService.model.Employee;
import com.employeesService.EmployeesService.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.employeeService = service;
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getAll();
    }

    @PostMapping
    public Employee save(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @GetMapping("/{empNo}")
    public Employee getEmployeeById(@PathVariable Long empNo) {
        return employeeService.getById(empNo);
    }


    @PutMapping("/{empNo}")
    public Employee updateEmployee(@PathVariable Long empNo, @RequestBody Employee employee) {
        return employeeService.update(empNo, employee);
    }

    @DeleteMapping("/{empNo}")
    public void deleteEmployee(@PathVariable Long empNo) {
        employeeService.delete(empNo);
    }

}
