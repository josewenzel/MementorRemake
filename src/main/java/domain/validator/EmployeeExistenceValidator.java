package domain.validator;

import domain.exception.EmployeeDoesNotExistsException;
import domain.model.Employee;
import domain.port.repository.EmployeeRepository;

public class EmployeeExistenceValidator {
    private final EmployeeRepository employeeRepository;

    public EmployeeExistenceValidator(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void validate(Employee employee) {
        if (employeeRepository.get(employee) == null)
            throw new EmployeeDoesNotExistsException();
    }
}
