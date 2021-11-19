package co.skyver.employees.service;

import co.skyver.employees.exception.DepartmentNotFoundException;
import co.skyver.employees.exception.EmployeeNotFoundException;
import co.skyver.employees.model.Department;
import co.skyver.employees.model.DepartmentEmployee;
import co.skyver.employees.model.Employee;
import co.skyver.employees.repository.DepartmentRepository;
import co.skyver.employees.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Employee save(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public Employee addEmployeeToDepartment(String deptNo, Long empNo, LocalDate fromDate) {
        Department department = this.departmentRepository.findById(deptNo).orElseThrow(() -> new DepartmentNotFoundException(deptNo));
        Employee employee = this.employeeRepository.findById(empNo).orElseThrow(() -> new EmployeeNotFoundException(empNo));
        department.addEmployee(employee, fromDate);
        this.departmentRepository.save(department);
        return employee;
    }

    @Override
    public List<DepartmentEmployee> listDepartmentEmployees(String deptNo) {
        Department department = this.departmentRepository.findByIdWithEmployees(deptNo).orElseThrow(() -> new DepartmentNotFoundException(deptNo));
        return department.getEmployees();
    }

    @Override
    public List<Employee> getAll() {
        return this.employeeRepository.findAll();
    }

    @Override
    public Employee getById(Long empNo) {
        Employee employee = this.employeeRepository.findById(empNo).orElseThrow(() -> new EmployeeNotFoundException(empNo));
        return employee;
    }

    @Override
    @Transactional
    public Employee update(Long empNo, Employee employee) {
        Employee foundEmployee = this.employeeRepository.findById(empNo).orElseThrow(() -> new EmployeeNotFoundException(empNo));
        foundEmployee.setFirstName(employee.getFirstName());
        foundEmployee.setLastName(employee.getLastName());
        foundEmployee.setBirthDate(employee.getBirthDate());
        foundEmployee.setHireDate(employee.getHireDate());
        foundEmployee.setGender(employee.getGender());
        return this.employeeRepository.save(foundEmployee);
    }

    @Override
    @Transactional
    public void delete(Long empNo) {
        this.employeeRepository.deleteById(empNo);
    }

    @Override
    @Transactional
    public void deleteEmployeeFromDepartment(String deptNo, Long empNo, LocalDate toDate) {
        Department department = this.departmentRepository.findByIdWithEmployees(deptNo).orElseThrow(() -> new DepartmentNotFoundException(deptNo));
        department.deleteEmployee(empNo, toDate);
        this.departmentRepository.save(department);
    }
}
