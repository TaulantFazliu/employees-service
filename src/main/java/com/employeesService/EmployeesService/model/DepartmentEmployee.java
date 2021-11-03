package com.employeesService.EmployeesService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "dept_emp")
public class DepartmentEmployee {

    @EmbeddedId
    private DepartmentEmployeeId id;

    @JsonIgnore
    @MapsId("deptNo")
    @JoinColumn(name = "dept_no")
    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    @JsonIgnore
    @MapsId("empNo")
    @JoinColumn(name = "emp_no")
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    DepartmentEmployee() {
    }

    public DepartmentEmployee(Department department, Employee employee) {
        this.department = department;
        this.employee = employee;
        this.id = new DepartmentEmployeeId(department.getDeptNo(), employee.getEmpNo());
    }

    public DepartmentEmployeeId getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
}
