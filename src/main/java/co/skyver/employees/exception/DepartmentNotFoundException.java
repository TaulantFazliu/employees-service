package co.skyver.employees.exception;

public class DepartmentNotFoundException extends RuntimeException {

    public DepartmentNotFoundException(String id) {
        super("Department not found for Id: " + id);
    }

}
