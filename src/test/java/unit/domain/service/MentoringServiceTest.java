package unit.domain.service;

import domain.model.Employee;
import domain.port.repository.EmployeeRepository;
import domain.service.MentoringService;
import fixture.EmployeeFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class MentoringServiceTest {
    @Mock
    EmployeeRepository employeeRepository;

    @Test
    public void requests_employee_and_mentor_to_store_to_add_a_mentor() {
        MentoringService mentoringService = new MentoringService(employeeRepository);
        Employee anEmployee = new EmployeeFixture().build();

        mentoringService.getMentorOf(anEmployee);

        then(employeeRepository).should().get(anEmployee);
    }
}