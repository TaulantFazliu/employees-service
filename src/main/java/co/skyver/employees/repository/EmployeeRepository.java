package co.skyver.employees.repository;

import co.skyver.employees.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    Employee save(Employee employee);

    List<Employee> findAll();

    Optional<Employee> findById(Long emptNo);

    Optional<Employee> findByIdWithSalaries(Long empNo);

    Optional<Employee> findByIdWithTitles(Long empNo);

    void deleteById(Long empNo);
}
