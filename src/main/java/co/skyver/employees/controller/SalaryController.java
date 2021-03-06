package co.skyver.employees.controller;

import co.skyver.employees.model.Salary;
import co.skyver.employees.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees/{empNo}/salaries")
public class SalaryController {

    private final SalaryService salaryService;

    @Autowired
    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @GetMapping
    public List<Salary> getSalaries(@PathVariable Long empNo) {
        return this.salaryService.listAllSalaries(empNo);
    }

    @PostMapping
    public Salary addSalary(@PathVariable Long empNo, @RequestBody Salary salary) {
        return this.salaryService.assignSalaryToEmployee(empNo, salary);
    }

}
