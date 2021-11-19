package co.skyver.employees.model;

import java.time.LocalDate;

public class DepartmentEmployeeDTO {

    private final LocalDate fromDate;
    private final LocalDate toDate;
    private final Employee employee;

    public DepartmentEmployeeDTO(LocalDate fromDate, LocalDate toDate, Employee employee) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.employee = employee;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public Employee getEmployee() {
        return employee;
    }

}
