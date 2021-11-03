package com.employeesService.EmployeesService.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DepartmentEmployeeId implements Serializable {

    @Column(name = "dept_no")
    private String deptNo;

    @Column(name = "emp_no")
    private Long empNo;

    DepartmentEmployeeId() {
    }

    public DepartmentEmployeeId(String deptNo, Long empNo) {
        this.deptNo = deptNo;
        this.empNo = empNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        DepartmentEmployeeId that = (DepartmentEmployeeId) o;
        return Objects.equals(deptNo, that.deptNo) &&
                Objects.equals(empNo, that.empNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deptNo, empNo);
    }
}
