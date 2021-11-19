package co.skyver.employees.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {

    public enum Gender {M, F}

    @Id
    private Long empNo;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @JsonIgnore
    @JoinColumn(name = "emp_no")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Salary> salaries = new ArrayList<>();

    @JsonIgnore
    @JoinColumn(name = "emp_no")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Title> titles = new ArrayList<>();

    public List<Salary> getSalaries() {
        return salaries;
    }

    public List<Title> getTitles() {
        return titles;
    }

    public Long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Long empNo) {
        this.empNo = empNo;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public void assignSalary(Salary salary) {
        salaries.add(salary);
    }

    public void assignTitle(Title title) {
        title.setEmpNo(this.empNo);
        for (Title title1 : titles) {
            if (title1.getToDate() == null) {
                title1.setToDate(title.getFromDate());
            }
        }
        titles.add(title);
    }

}
