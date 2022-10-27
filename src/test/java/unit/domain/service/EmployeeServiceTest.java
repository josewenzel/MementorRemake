package unit.domain.service;

import domain.exception.DuplicateEmployeeException;
import domain.model.Employee;
import domain.port.repository.EmployeeRepository;
import domain.service.EmployeeService;
import domain.validator.DuplicatedEmployeeValidator;
import fixture.EmployeeFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
    @Mock
    EmployeeRepository employeeRepository;
    @Mock
    DuplicatedEmployeeValidator duplicatedEmployeeValidator;
    private EmployeeService employeeService;
    private Employee anEmployee;

    @BeforeEach
    void setUp() {
        anEmployee = new EmployeeFixture().build();
        employeeService = new EmployeeService(employeeRepository, duplicatedEmployeeValidator);
    }

    @Test
    public void request_an_employee_to_be_stored() {
        employeeService.addEmployee(anEmployee);

        then(employeeRepository).should().add(anEmployee);
    }

    @Test
    public void throw_an_exception_if_trying_to_store_a_duplicated_employee() {
        doThrow(DuplicateEmployeeException.class).when(duplicatedEmployeeValidator).validate(anEmployee);

        assertThatThrownBy(() -> employeeService.addEmployee(anEmployee))
                .isInstanceOf(DuplicateEmployeeException.class);
    }

    @Test
    public void request_an_employee_from_the_store() {
        employeeService.getEmployee(anEmployee);

        then(employeeRepository).should().get(anEmployee);
    }

    @Test
    public void request_all_employees_from_store() {
        employeeService.getAllEmployees();

        then(employeeRepository).should().getAll();
    }

    @Test
    public void request_an_employee_to_be_removed_from_store() {
        employeeService.removeEmployee(anEmployee);

        then(employeeRepository).should().remove(anEmployee);
    }
}