package domain.port.repository;

import domain.model.Employee;

import java.util.UUID;

public interface EmployeeRepository {
    void add(Employee employee);

    Employee get(Employee employee);

    void update(UUID oldEmployeeId, Employee newEmployee);
}
