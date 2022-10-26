package feature;

import domain.exception.DuplicateEmployeeException;
import domain.model.Employee;
import domain.port.repository.EmployeeRepository;
import domain.service.EmployeeService;
import domain.validator.DuplicatedEmployeeValidator;
import doubles.FakeEmployeeRepository;
import fixture.EmployeeFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EmployeeServiceFeatureTest {

    private final Employee anEmployee = new EmployeeFixture().build();
    private final Employee anotherEmployee = new EmployeeFixture().build();
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        EmployeeRepository employeeRepository = new FakeEmployeeRepository();
        DuplicatedEmployeeValidator duplicatedEmployeeValidator = new DuplicatedEmployeeValidator(employeeRepository);
        employeeService = new EmployeeService(employeeRepository, duplicatedEmployeeValidator);
    }

    @Test
    public void retrieves_an_employee_if_this_exists() {
        employeeService.addEmployee(anEmployee);

        assertThat(employeeService.getEmployee(anEmployee)).isEqualTo(anEmployee);
    }

    @Test
    public void disallow_a_new_employee_if_already_exists() {
        employeeService.addEmployee(anEmployee);

        assertThatThrownBy(() -> employeeService.addEmployee(anEmployee))
                .isInstanceOf(DuplicateEmployeeException.class);
    }

    @Test
    public void removes_an_employee() {
        employeeService.addEmployee(anEmployee);
        employeeService.removeEmployee(anEmployee);

        assertThat(employeeService.getEmployee(anEmployee)).isNull();
    }

    @Test
    public void retrieves_all_employees() {
        employeeService.addEmployee(anEmployee);
        employeeService.addEmployee(anotherEmployee);

        assertThat(employeeService.getAllEmployees()).contains(anEmployee, anotherEmployee);
    }
}
