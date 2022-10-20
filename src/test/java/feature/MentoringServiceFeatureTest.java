package feature;

import domain.model.Employee;
import domain.port.repository.EmployeeRepository;
import domain.service.MentoringService;
import doubles.FakeEmployeeRepository;
import fixture.EmployeeFixture;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class MentoringServiceFeatureTest {
    private final Employee employee = new EmployeeFixture().build();
    private final Employee mentor = new EmployeeFixture().withId(UUID.randomUUID()).build();

    @Test
    public void adds_a_mentor_to_an_existing_employee() {
        EmployeeRepository employeeRepository = repositoryWithEmployees();
        MentoringService mentoringService = new MentoringService(employeeRepository);

        mentoringService.addMentor(employee, mentor);

        Employee mentorOfEmployee = mentoringService.getMentorOf(employee);
        assertThat(mentorOfEmployee).isEqualTo(mentor);
    }

    private EmployeeRepository repositoryWithEmployees() {
        EmployeeRepository employeeRepository = new FakeEmployeeRepository();
        employeeRepository.add(employee);
        employeeRepository.add(mentor);
        return employeeRepository;
    }
}
