package unit.domain.service;

import domain.exception.MentorDoesNotExistException;
import domain.model.Employee;
import domain.port.repository.EmployeeRepository;
import domain.service.MentoringService;
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
class MentoringServiceTest {
    @Mock
    EmployeeRepository employeeRepository;
    MentoringService mentoringService;
    Employee anEmployee = new EmployeeFixture().build();
    Employee mentor = new EmployeeFixture().build();

    @BeforeEach
    void setUp() {
        mentoringService = new MentoringService(employeeRepository);
    }

    @Test
    public void requests_employee_and_mentor_to_store_to_add_a_mentor() {
        when(employeeRepository.get(mentor)).thenReturn(mentor);
        mentoringService.addMentor(anEmployee, mentor);

        Employee mentoredEmployee = new EmployeeFixture()
                .withId(anEmployee.id())
                .withPersonalInformation(anEmployee.personalInformation())
                .withMentor(mentor).build();

        then(employeeRepository).should().update(anEmployee, mentoredEmployee);
    }

    @Test
    public void disallow_to_add_a_mentor_to_employee_if_mentor_does_not_exist() {
        when(employeeRepository.get(mentor)).thenReturn(null);

        assertThatThrownBy(() -> mentoringService.addMentor(anEmployee, mentor))
                .isInstanceOf(MentorDoesNotExistException.class);
    }
}