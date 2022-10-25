package domain.service;

import domain.exception.EmployeeDoesNotExistsException;
import domain.exception.MentorDoesNotExistException;
import domain.exception.SelfMentorException;
import domain.model.Employee;
import domain.port.repository.EmployeeRepository;

public class MentoringService {
    private final EmployeeRepository employeeRepository;

    public MentoringService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void addMentor(Employee employee, Employee mentor) {
        // TODO move to validation objects
        if (isNotAMember(employee)) throw new EmployeeDoesNotExistsException();
        if (isNotAMember(mentor)) throw new MentorDoesNotExistException();
        if (employee == mentor) throw new SelfMentorException();

        employee.addMentor(mentor);
        employeeRepository.update(employee.id(), employee);
    }

    public Employee getMentorOf(Employee employee) {
        Employee requestedEmployee = employeeRepository.get(employee);
        return requestedEmployee.mentor();
    }

    public void removeMentor(Employee employee) {
        if (isNotAMember(employee)) throw new EmployeeDoesNotExistsException();

        employee.removeMentor();
        employeeRepository.update(employee.id(), employee);
    }

    private boolean isNotAMember(Employee employee) {
        return employeeRepository.get(employee) == null;
    }
}
