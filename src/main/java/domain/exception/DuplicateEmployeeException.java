package domain.exception;

public class DuplicateEmployeeException extends RuntimeException {
    public DuplicateEmployeeException() {
        super("This employee already exists");
    }

}
