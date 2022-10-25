package feature;

import domain.exception.DuplicateEmployeeException;
import domain.model.Employee;
import domain.port.repository.EmployeeRepository;
import domain.service.EmployeeService;
import doubles.FakeEmployeeRepository;
import fixture.EmployeeFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EmployeeServiceFeatureTest {

    private final Employee AN_EMPLOYEE = new EmployeeFixture().build();
    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeRepository = new FakeEmployeeRepository();
        employeeService = new EmployeeService(employeeRepository);
    }

    @Test
    public void retrieves_an_employee_if_this_exists() {
        employeeService.addEmployee(AN_EMPLOYEE);

        assertThat(employeeService.getEmployee(AN_EMPLOYEE)).isEqualTo(AN_EMPLOYEE);
    }

    @Test
    public void disallow_a_new_employee_if_already_exists() {
        employeeService.addEmployee(AN_EMPLOYEE);

        assertThatThrownBy(() -> employeeService.addEmployee(AN_EMPLOYEE))
                .isInstanceOf(DuplicateEmployeeException.class);
    }

    @Test
    public void removes_an_employee() {
        employeeService.addEmployee(AN_EMPLOYEE);
        employeeService.removeEmployee(AN_EMPLOYEE);

        assertThat(employeeService.getEmployee(AN_EMPLOYEE)).isNull();
    }
}
