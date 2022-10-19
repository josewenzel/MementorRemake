package domain.port.repository;

import domain.model.Employee;

public interface EmployeeRepository {
    void add(Employee employee);

    Employee get(Employee employee);
}
