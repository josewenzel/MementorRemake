package fixture;

import domain.model.Employee;
import domain.model.PersonalInformation;
import domain.model.Seniority;

import java.util.UUID;

public class EmployeeFixture {
    PersonalInformation personalInformation;
    UUID id;
    Employee mentor;

    public EmployeeFixture() {
        id = UUID.randomUUID();
        personalInformation = new PersonalInformation("Bilbo", "Baggins", Seniority.Craftsperson);
    }

    public EmployeeFixture withId(UUID id) {
        this.id = id;
        return this;
    }

    public Employee build() {
        return new Employee(id, personalInformation, mentor);
    }

    public EmployeeFixture withPersonalInformation(PersonalInformation personalInformation) {
        this.personalInformation = personalInformation;
        return this;
    }

    public EmployeeFixture withMentor(Employee mentor) {
        this.mentor = mentor;
        return this;
    }
}
