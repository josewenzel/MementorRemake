package doubles;

import domain.model.Employee;
import domain.port.repository.EmployeeRepository;

import java.util.HashMap;
import java.util.UUID;

public class FakeEmployeeRepository implements EmployeeRepository {
    HashMap<UUID, Employee> employees = new HashMap<>();

    @Override
    public void add(Employee employee) {
        employees.put(employee.id(), employee);
    }

    @Override
    public Employee get(Employee employee) {
        return employees.get(employee.id());
    }

    @Override
    public void update(UUID employeeId, Employee updatedEmployee) {
        employees.remove(employeeId);
        employees.put(updatedEmployee.id(), updatedEmployee);
    }

    @Override
    public void remove(Employee employee) {
        employees.remove(employee.id());
    }
}
