package com.employeesService.EmployeesService.model;

import com.employeesService.EmployeesService.exception.EmployeeNotFoundException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @Column(name = "dept_no")
    private String deptNo;

    @Column(name = "dept_name")
    private String deptName;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department", orphanRemoval = true)
    private List<DepartmentEmployee> employees = new ArrayList<>();

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void addEmployee(Employee employee, LocalDate fromDate) {
        DepartmentEmployee employeeToAdd = new DepartmentEmployee(this, employee);
        employeeToAdd.setFromDate(fromDate);
        employees.add(employeeToAdd);
    }

    public void deleteEmployee(Long empNo, LocalDate toDate) {
        DepartmentEmployeeId id = new DepartmentEmployeeId(this.deptNo, empNo);
        this.employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException(empNo))
                .setToDate(toDate);
    }

    public List<DepartmentEmployee> getEmployees() {
        return employees;
    }
}
