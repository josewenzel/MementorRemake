package domain.service;

import domain.model.Employee;
import domain.port.repository.EmployeeRepository;
import domain.validator.DuplicatedEmployeeValidator;

import java.util.List;

public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private DuplicatedEmployeeValidator duplicatedEmployeeValidator;

    public EmployeeService(EmployeeRepository employeeRepository, DuplicatedEmployeeValidator duplicatedEmployeeValidator) {
        this.employeeRepository = employeeRepository;
        this.duplicatedEmployeeValidator = duplicatedEmployeeValidator;
    }

    public Employee getEmployee(Employee employee) {
        return employeeRepository.get(employee);
    }

    public void addEmployee(Employee employee) {
        duplicatedEmployeeValidator.validate(employee);
        employeeRepository.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employeeRepository.remove(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAll();
    }
}
