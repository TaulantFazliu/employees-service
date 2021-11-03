package com.employeesService.EmployeesService.controller;

import com.employeesService.EmployeesService.model.Department;
import com.employeesService.EmployeesService.model.DepartmentEmployee;
import com.employeesService.EmployeesService.model.DepartmentEmployeeDTO;
import com.employeesService.EmployeesService.model.Employee;
import com.employeesService.EmployeesService.service.DepartmentService;
import com.employeesService.EmployeesService.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;
    private final EmployeeService employeeService;

    @Autowired
    public DepartmentController(DepartmentService departmentService, EmployeeService employeeService) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Department> getDepartments() {
        return departmentService.getAll();
    }

    @PostMapping
    public Department save(@RequestBody Department department) {
        return departmentService.save(department);
    }

    @PostMapping("/{deptNo}/employees/{empNo}")
    public Employee addEmpToDept(@PathVariable String deptNo, @PathVariable Long empNo, @RequestBody DepartmentEmployee departmentEmployee) {
        return this.employeeService.addEmployeeToDepartment(deptNo, empNo, departmentEmployee.getFromDate());
    }

    @GetMapping("/{deptNo}")
    public Department getDepartmentById(@PathVariable String deptNo) {
        return departmentService.getById(deptNo);
    }

    @GetMapping("/{deptNo}/employees")
    public List<DepartmentEmployeeDTO> getEmployeesPerDepartment(@PathVariable String deptNo) {
        return this.employeeService.listDepartmentEmployees(deptNo).stream().map(DepartmentController::convertToDTO).collect(Collectors.toList());
    }

    @PutMapping("/{deptNo}")
    public Department updateDepartment(@PathVariable String deptNo, @RequestBody Department department) {
        return departmentService.update(deptNo, department);
    }

    @DeleteMapping("/{deptNo}")
    public void deleteDepartment(@PathVariable String deptNo) {
        departmentService.delete(deptNo);
    }

    @DeleteMapping("/{deptNo}/employees/{empNo}")
    public void deleteEmployeeFromDepartment(@PathVariable String deptNo, @PathVariable Long empNo, @RequestBody DepartmentEmployee departmentEmployee) {
        this.employeeService.deleteEmployeeFromDepartment(deptNo, empNo, departmentEmployee.getToDate());
    }

    private static DepartmentEmployeeDTO convertToDTO(DepartmentEmployee employee) {
        return new DepartmentEmployeeDTO(employee.getFromDate(), employee.getToDate(), employee.getEmployee());
    }
}
