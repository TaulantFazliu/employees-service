package com.employeesService.EmployeesService.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "salaries")
@IdClass(Salary.SalaryId.class)
public class Salary {

    @Id
    @Column(name = "emp_no")
    private Long empNo;

    @Column(name = "salary")
    private Long salary;
    @Id
    @Column(name = "from_date")
    private LocalDate fromDate;
    @Column(name = "to_date")
    private LocalDate toDate;

    public Salary() {
    }

    public Salary(Long empNo) {
        this.empNo = empNo;
    }

    public Long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Long empNo) {
        this.empNo = empNo;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
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

    static class SalaryId implements Serializable {
        private Long empNo;

        private LocalDate fromDate;

        public SalaryId() {
        }

        ;

        public SalaryId(Long empNo, LocalDate fromDate) {
            this.empNo = empNo;
            this.fromDate = fromDate;
        }

    }
}