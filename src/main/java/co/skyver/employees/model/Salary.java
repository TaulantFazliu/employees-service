package co.skyver.employees.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "salaries")
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

    Salary() {
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

    public LocalDate getFromDate() {
        return fromDate;
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

        public SalaryId(Long empNo, LocalDate fromDate) {
            this.empNo = empNo;
            this.fromDate = fromDate;
        }
    }
}
