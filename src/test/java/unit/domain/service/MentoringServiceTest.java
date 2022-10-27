package unit.domain.service;

import domain.exception.EmployeeDoesNotExistsException;
import domain.exception.SelfMentorException;
import domain.model.Employee;
import domain.port.repository.EmployeeRepository;
import domain.service.MentoringService;
import domain.validator.EmployeeExistenceValidator;
import fixture.EmployeeFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MentoringServiceTest {
    @Mock
    EmployeeRepository employeeRepository;
    @Mock
    EmployeeExistenceValidator employeeExistenceValidator;
    MentoringService mentoringService;

    Employee employeeWithMentor = new EmployeeFixture().build();
    private final Employee employeeWithoutMentor = new EmployeeFixture()
            .withId(employeeWithMentor.id())
            .withPersonalInformation(employeeWithMentor.personalInformation())
            .withMentor(null).build();
    Employee mentor = new EmployeeFixture().build();
    private final Employee mentoredEmployee = new EmployeeFixture()
            .withId(employeeWithMentor.id())
            .withPersonalInformation(employeeWithMentor.personalInformation())
            .withMentor(mentor).build();

    @BeforeEach
    void setUp() {
        mentoringService = new MentoringService(employeeRepository, employeeExistenceValidator);
    }

    @Test
    public void requests_to_add_a_mentor_to_employee() {
        mentoringService.addMentor(employeeWithMentor, mentor);

        then(employeeRepository).should().update(employeeWithMentor.id(), mentoredEmployee);
    }

    @Test
    public void disallow_mentor_a_non_existent_employee() {
        doThrow(EmployeeDoesNotExistsException.class).when(employeeExistenceValidator).validate(employeeWithMentor);

        assertThatThrownBy(() -> mentoringService.addMentor(employeeWithMentor, mentor))
                .isInstanceOf(EmployeeDoesNotExistsException.class);
    }

    @Test
    public void disallow_to_be_mentored_by_a_non_existent_mentor() {
        doNothing().when(employeeExistenceValidator).validate(employeeWithMentor);
        doThrow(EmployeeDoesNotExistsException.class).when(employeeExistenceValidator).validate(mentor);

        assertThatThrownBy(() -> mentoringService.addMentor(employeeWithMentor, mentor))
                .isInstanceOf(EmployeeDoesNotExistsException.class);
    }

    @Test
    public void disallow_to_mentor_yourself() {
        assertThatThrownBy(() -> mentoringService.addMentor(employeeWithMentor, employeeWithMentor))
                .isInstanceOf(SelfMentorException.class);
    }

    @Test
    public void request_to_remove_mentoring_relationship() {
        mentoringService.removeMentor(employeeWithMentor);

        then(employeeRepository).should().update(employeeWithMentor.id(), employeeWithoutMentor);
    }
}