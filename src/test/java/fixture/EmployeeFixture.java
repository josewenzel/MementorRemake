package fixture;

import domain.model.Employee;

import java.util.UUID;

public class EmployeeFixture {
    UUID id;

    public EmployeeFixture() {
        id = UUID.randomUUID();
    }

    public Employee build() {
        return new Employee(id);
    }
}
