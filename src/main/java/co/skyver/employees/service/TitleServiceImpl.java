package co.skyver.employees.service;

import co.skyver.employees.exception.EmployeeNotFoundException;
import co.skyver.employees.model.Employee;
import co.skyver.employees.model.Title;
import co.skyver.employees.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TitleServiceImpl implements TitleService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public TitleServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional
    public Title assignTitleToEmployee(Long empNo, Title titleToAdd) {
        Employee employee = this.employeeRepository.findById(empNo).orElseThrow(() -> new EmployeeNotFoundException(empNo));
        employee.assignTitle(titleToAdd);
        this.employeeRepository.save(employee);
        return titleToAdd;
    }

    @Override
    public List<Title> listAllTitles(Long empNo) {
        Employee employee = this.employeeRepository.findByIdWithTitles(empNo).orElseThrow(() -> new EmployeeNotFoundException(empNo));
        return employee.getTitles();
    }
}
