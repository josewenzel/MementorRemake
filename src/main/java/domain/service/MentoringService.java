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
        if (!isAMember(employee)) throw new EmployeeDoesNotExistsException();
        if (!isAMember(mentor)) throw new MentorDoesNotExistException();
        if (employee == mentor) throw new SelfMentorException();

        employee.addMentor(mentor);
        employeeRepository.update(employee.id(), employee);
    }

    public Employee getMentorOf(Employee employee) {
        Employee requestedEmployee = employeeRepository.get(employee);
        return requestedEmployee.mentor();
    }

    private boolean isAMember(Employee employee) {
        return employeeRepository.get(employee) != null;
    }
}
