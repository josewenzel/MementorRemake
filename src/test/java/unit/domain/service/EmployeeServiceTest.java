package unit.domain.service;

import domain.exception.DuplicateEmployeeException;
import domain.model.Employee;
import domain.port.repository.EmployeeRepository;
import domain.service.EmployeeService;
import fixture.EmployeeFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
    @Mock
    EmployeeRepository employeeRepository;
    private EmployeeService employeeService;
    private Employee anEmployee;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeService(employeeRepository);
        anEmployee = new EmployeeFixture().build();
    }

    @Test
    public void request_an_employee_to_be_stored() {
        employeeService.addNewEmployee(anEmployee);

        then(employeeRepository).should().add(anEmployee);
    }

    @Test
    public void request_an_employee_from_the_store() {
        employeeService.getEmployee(anEmployee);

        then(employeeRepository).should().get(anEmployee);
    }

    @Test
    public void throw_an_exception_if_trying_to_store_a_duplicated_employee() {
        when(employeeRepository.get(anEmployee)).thenReturn(anEmployee);

        assertThatThrownBy(() -> employeeService.addNewEmployee(anEmployee))
                .isInstanceOf(DuplicateEmployeeException.class);
    }
}