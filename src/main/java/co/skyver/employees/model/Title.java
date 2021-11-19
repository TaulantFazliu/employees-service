package co.skyver.employees.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "titles")
@IdClass(Title.TitleId.class)
public class Title {

    @Id
    @Column(name = "emp_no")
    private Long empNo;

    @Id
    @Column(name = "title")
    private String title;

    @Id
    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    Title() {
    }

    public Long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Long empNo) {
        this.empNo = empNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    static class TitleId implements Serializable {
        private Long empNo;

        private String title;

        private LocalDate fromDate;

        TitleId() {
        }

        public TitleId(Long empNo, String title, LocalDate fromDate) {
            this.empNo = empNo;
            this.title = title;
            this.fromDate = fromDate;
        }
    }
}
