package domain.port.repository;

import domain.model.Employee;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository {
    void add(Employee employee);

    Employee get(Employee employee);

    void update(UUID employeeId, Employee updatedEmployee);

    void remove(Employee employee);

    List<Employee> getAll();
}
