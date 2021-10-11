package com.employeesService.EmployeesService.exception;

public class DepartmentNotFoundException extends RuntimeException {

    public DepartmentNotFoundException(String id) {
        super("Department not found for Id: " + id);
    }
}
