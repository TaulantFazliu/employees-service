package co.skyver.employees.service;

import co.skyver.employees.exception.EmployeeNotFoundException;
import co.skyver.employees.model.Employee;
import co.skyver.employees.model.Salary;
import co.skyver.employees.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SalaryServiceImpl implements SalaryService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public SalaryServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional
    public Salary assignSalaryToEmployee(Long empNo, Salary salaryToAdd) {
        Employee employee = this.employeeRepository.findById(empNo).orElseThrow(() -> new EmployeeNotFoundException(empNo));
        salaryToAdd.setEmpNo(empNo);
        List<Salary> salaries = employee.getSalaries();
        for (Salary salary : salaries) {
            if (salary.getToDate() == null) {
                salary.setToDate(salaryToAdd.getFromDate());
            }
        }
        employee.assignSalary(salaryToAdd);
        this.employeeRepository.save(employee);
        return salaryToAdd;
    }

    @Override
    public List<Salary> listAllSalaries(Long empNo) {
        Employee employee = this.employeeRepository.findByIdWithSalaries(empNo).orElseThrow(() -> new EmployeeNotFoundException(empNo));
        return employee.getSalaries();
    }
}
