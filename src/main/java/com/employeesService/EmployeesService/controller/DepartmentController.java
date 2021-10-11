package com.employeesService.EmployeesService.controller;

import com.employeesService.EmployeesService.model.Department;
import com.employeesService.EmployeesService.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<Department> getDepartments() {
        return departmentService.getAll();
    }

    @PostMapping
    public Department save(@RequestBody Department department) {
        return departmentService.save(department);
    }

    @GetMapping("/{deptNo}")
    public Department getDepartmentById(@PathVariable String deptNo) {
        return departmentService.getById(deptNo);
    }

    @PutMapping("/{deptNo}")
    public Department updateDepartment(@PathVariable String deptNo, @RequestBody Department department) {
        return departmentService.update(deptNo, department);
    }

    @DeleteMapping("/{deptNo}")
    public void deleteDepartment(@PathVariable String deptNo) {
        departmentService.delete(deptNo);
    }


}
