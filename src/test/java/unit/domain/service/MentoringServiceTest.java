package unit.domain.service;

import domain.exception.EmployeeDoesNotExistsException;
import domain.exception.MentorDoesNotExistException;
import domain.exception.SelfMentorException;
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
        mentoringService = new MentoringService(employeeRepository);
    }

    @Test
    public void requests_to_add_a_mentor_to_employee() {
        when(employeeRepository.get(mentor)).thenReturn(mentor);
        when(employeeRepository.get(employeeWithMentor)).thenReturn(employeeWithMentor);

        mentoringService.addMentor(employeeWithMentor, mentor);

        then(employeeRepository).should().update(employeeWithMentor.id(), mentoredEmployee);
    }

    @Test
    public void disallow_mentor_a_non_existent_employee() {
        when(employeeRepository.get(employeeWithMentor)).thenReturn(null);

        assertThatThrownBy(() -> mentoringService.addMentor(employeeWithMentor, mentor))
                .isInstanceOf(EmployeeDoesNotExistsException.class);
    }

    @Test
    public void disallow_to_be_mentored_by_a_non_existent_mentor() {
        when(employeeRepository.get(employeeWithMentor)).thenReturn(employeeWithMentor);
        when(employeeRepository.get(mentor)).thenReturn(null);

        assertThatThrownBy(() -> mentoringService.addMentor(employeeWithMentor, mentor))
                .isInstanceOf(MentorDoesNotExistException.class);
    }

    @Test
    public void disallow_to_mentor_yourself() {
        when(employeeRepository.get(employeeWithMentor)).thenReturn(employeeWithMentor);

        assertThatThrownBy(() -> mentoringService.addMentor(employeeWithMentor, employeeWithMentor))
                .isInstanceOf(SelfMentorException.class);
    }

    @Test
    public void request_to_remove_mentoring_relationship() {
        when(employeeRepository.get(employeeWithMentor)).thenReturn(employeeWithMentor);

        mentoringService.removeMentor(employeeWithMentor);

        then(employeeRepository).should().update(employeeWithMentor.id(), employeeWithoutMentor);
    }
}