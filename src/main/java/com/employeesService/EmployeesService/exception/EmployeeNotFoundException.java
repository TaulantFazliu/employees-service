package com.employeesService.EmployeesService.exception;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(Long id) {
        super("Employee not found for id: " + id);
    }
}
