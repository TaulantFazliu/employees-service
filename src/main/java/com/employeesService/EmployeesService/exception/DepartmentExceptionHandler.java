package com.employeesService.EmployeesService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DepartmentExceptionHandler {

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<DepartmentException> departmentNotFound(DepartmentNotFoundException d) {
        DepartmentException departmentException = new DepartmentException();
        departmentException.setMessage(d.getMessage());
        departmentException.setStatusCode(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(departmentException, HttpStatus.NOT_FOUND);
    }

}
