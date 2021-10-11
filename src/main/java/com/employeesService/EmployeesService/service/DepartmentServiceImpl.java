package com.employeesService.EmployeesService.service;

import com.employeesService.EmployeesService.exception.DepartmentNotFoundException;
import com.employeesService.EmployeesService.model.Department;
import com.employeesService.EmployeesService.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getById(String deptNo) {
        Department department = departmentRepository.findById(deptNo).orElseThrow(() -> new DepartmentNotFoundException(deptNo));
        return department;
    }

    @Override
    public Department update(String deptNo, Department department) {
        department.setDeptNo(deptNo);
        return departmentRepository.update(department);
    }

    @Override
    public void delete(String deptNo) {
        departmentRepository.deleteById(deptNo);
    }
}
