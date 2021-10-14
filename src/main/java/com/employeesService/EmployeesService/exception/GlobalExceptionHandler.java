package com.employeesService.EmployeesService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({DepartmentNotFoundException.class})
    public ResponseEntity<ApiError> departmentNotFound(DepartmentNotFoundException d) {
        ApiError departmentException = new ApiError();
        departmentException.setMessage(d.getMessage());
        departmentException.setStatusCode(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(departmentException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ApiError> employeeNotFound(EmployeeNotFoundException e) {
        ApiError employeeException = new ApiError();
        employeeException.setMessage(e.getMessage());
        employeeException.setStatusCode(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(employeeException, HttpStatus.NOT_FOUND);
    }

}
