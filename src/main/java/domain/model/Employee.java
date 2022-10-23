package domain.model;

import java.util.Objects;
import java.util.UUID;

public final class Employee {
    private final UUID id;
    private final PersonalInformation personalInformation;
    private Employee mentor;

    public Employee(UUID id, PersonalInformation personalInformation, Employee mentor) {
        this.id = id;
        this.personalInformation = personalInformation;
        this.mentor = mentor;
    }

    public void addMentor(Employee mentor) {
        this.mentor = mentor;
    }

    public void removeMentor() {
        this.mentor = null;
    }

    public UUID id() {
        return id;
    }

    public PersonalInformation personalInformation() {
        return personalInformation;
    }

    public Employee mentor() {
        return mentor;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Employee) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.personalInformation, that.personalInformation) &&
                Objects.equals(this.mentor, that.mentor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personalInformation, mentor);
    }
}
