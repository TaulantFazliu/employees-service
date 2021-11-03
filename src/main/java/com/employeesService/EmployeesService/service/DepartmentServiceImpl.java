package com.employeesService.EmployeesService.service;

import com.employeesService.EmployeesService.exception.DepartmentNotFoundException;
import com.employeesService.EmployeesService.model.Department;
import com.employeesService.EmployeesService.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional
    public Department save(Department department) {
        return this.departmentRepository.save(department);
    }

    @Override
    public List<Department> getAll() {
        return this.departmentRepository.findAll();
    }

    @Override
    public Department getById(String deptNo) {
        Department department = this.departmentRepository.findById(deptNo).orElseThrow(() -> new DepartmentNotFoundException(deptNo));
        return department;
    }

    @Override
    @Transactional
    public Department update(String deptNo, Department department) {
        Department foundDepartment = this.departmentRepository.findById(deptNo).orElseThrow(() -> new DepartmentNotFoundException(deptNo));
        foundDepartment.setDeptName(department.getDeptName());
        return this.departmentRepository.save(foundDepartment);
    }

    @Override
    @Transactional
    public void delete(String deptNo) {
        this.departmentRepository.deleteById(deptNo);
    }
}
