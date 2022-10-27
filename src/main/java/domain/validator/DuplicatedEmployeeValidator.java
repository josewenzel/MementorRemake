package domain.validator;

import domain.exception.DuplicateEmployeeException;
import domain.model.Employee;
import domain.port.repository.EmployeeRepository;

public class DuplicatedEmployeeValidator {
    private final EmployeeRepository employeeRepository;

    public DuplicatedEmployeeValidator(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void validate(Employee employee) {
        if (employeeRepository.get(employee) != null)
            throw new DuplicateEmployeeException();
    }
}
