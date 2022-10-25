package domain.service;

import domain.exception.DuplicateEmployeeException;
import domain.model.Employee;
import domain.port.repository.EmployeeRepository;

import java.util.List;

public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployee(Employee employee) {
        return employeeRepository.get(employee);
    }

    public void addEmployee(Employee employee) {
        if (isAMember(employee))
            throw new DuplicateEmployeeException();
        employeeRepository.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employeeRepository.remove(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAll();
    }

    private boolean isAMember(Employee employee) {
        return employeeRepository.get(employee) != null;
    }
}
