package domain.validator;

import domain.exception.EmployeeDoesNotExistsException;
import domain.model.Employee;
import domain.port.repository.EmployeeRepository;

public class EmployeeExistenceValidator {
    private EmployeeRepository employeeRepository;

    public EmployeeExistenceValidator(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Void validate(Employee employee) {
        if (employeeRepository.get(employee) == null)
            throw new EmployeeDoesNotExistsException();
        return null;
    }
}
