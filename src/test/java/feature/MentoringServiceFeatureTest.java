package feature;

import domain.model.Employee;
import domain.port.repository.EmployeeRepository;
import domain.service.MentoringService;
import domain.validator.EmployeeExistenceValidator;
import doubles.FakeEmployeeRepository;
import fixture.EmployeeFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MentoringServiceFeatureTest {
    private final Employee employee = new EmployeeFixture().build();
    private final Employee mentor = new EmployeeFixture().build();
    private MentoringService mentoringService;

    @BeforeEach
    void setUp() {
        EmployeeRepository employeeRepository = repositoryWithEmployees();
        EmployeeExistenceValidator employeeExistenceValidator = new EmployeeExistenceValidator(employeeRepository);
        mentoringService = new MentoringService(employeeRepository, employeeExistenceValidator);
    }

    @Test
    public void adds_a_mentor_to_an_existing_employee() {
        mentoringService.addMentor(employee, mentor);

        Employee mentorOfEmployee = mentoringService.getMentorOf(employee);
        assertThat(mentorOfEmployee).isEqualTo(mentor);
    }

    @Test
    public void removes_a_mentor_to_an_existing_employee() {
        mentoringService.addMentor(employee, mentor);

        mentoringService.removeMentor(employee);

        Employee mentorOfEmployee = mentoringService.getMentorOf(employee);
        assertThat(mentorOfEmployee).isNull();
    }

    private EmployeeRepository repositoryWithEmployees() {
        EmployeeRepository employeeRepository = new FakeEmployeeRepository();
        employeeRepository.add(employee);
        employeeRepository.add(mentor);
        return employeeRepository;
    }
}
