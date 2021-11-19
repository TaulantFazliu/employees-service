package co.skyver.employees.service;

import co.skyver.employees.model.DepartmentEmployee;
import co.skyver.employees.model.Employee;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {

    Employee save(Employee employee);

    Employee addEmployeeToDepartment(String deptNo, Long empNo, LocalDate fromDate);

    List<DepartmentEmployee> listDepartmentEmployees(String deptNo);

    List<Employee> getAll();

    Employee getById(Long empNo);

    Employee update(Long empNo, Employee employee);

    void delete(Long empNo);

    void deleteEmployeeFromDepartment(String deptNo, Long empNo, LocalDate fromDate);
}
