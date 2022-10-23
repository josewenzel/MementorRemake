package domain.service;

import domain.exception.DuplicateEmployeeException;
import domain.model.Employee;
import domain.port.repository.EmployeeRepository;

public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployee(Employee employee) {
        return employeeRepository.get(employee);
    }

    public void addNewEmployee(Employee employee) {
        if (isAMember(employee))
            throw new DuplicateEmployeeException();
        employeeRepository.add(employee);
    }

    private boolean isAMember(Employee employee) {
        return getEmployee(employee) != null;
    }
}
