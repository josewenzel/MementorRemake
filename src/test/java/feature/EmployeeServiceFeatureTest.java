package feature;

import domain.exception.DuplicateEmployeeException;
import domain.model.Employee;
import domain.port.repository.EmployeeRepository;
import domain.service.EmployeeService;
import doubles.FakeEmployeeRepository;
import fixture.EmployeeFixture;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EmployeeServiceFeatureTest {
    @Test
    public void retrieves_an_employee_if_this_exists() {
        Employee employee = new EmployeeFixture().build();
        EmployeeRepository employeeRepository = new FakeEmployeeRepository();
        EmployeeService employeeService = new EmployeeService(employeeRepository);

        employeeService.addNewEmployee(employee);
        Employee retrievedEmployee = employeeService.getEmployee(employee);

        assertThat(retrievedEmployee).isEqualTo(employee);
    }

    @Test
    public void disallow_a_new_employee_if_already_exists() {
        Employee employee = new EmployeeFixture().build();
        EmployeeRepository employeeRepository = new FakeEmployeeRepository();
        EmployeeService employeeService = new EmployeeService(employeeRepository);

        employeeService.addNewEmployee(employee);

        assertThatThrownBy(() -> employeeService.addNewEmployee(employee))
                .isInstanceOf(DuplicateEmployeeException.class);
    }
}
