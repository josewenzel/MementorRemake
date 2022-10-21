package domain.port.repository;

import domain.model.Employee;

public interface EmployeeRepository {
    void add(Employee employee);

    Employee get(Employee employee);

    void update(Employee oldEmployee, Employee newEmployee);
}
