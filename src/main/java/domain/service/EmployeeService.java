package domain.service;

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
        employeeRepository.add(employee);
    }
}
