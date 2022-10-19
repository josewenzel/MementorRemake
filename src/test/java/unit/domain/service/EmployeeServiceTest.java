package unit.domain.service;

import domain.model.Employee;
import domain.port.repository.EmployeeRepository;
import domain.service.EmployeeService;
import fixture.EmployeeFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.then;

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
}