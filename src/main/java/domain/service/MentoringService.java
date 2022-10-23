package domain.service;

import domain.exception.EmployeeDoesNotExistsException;
import domain.exception.MentorDoesNotExistException;
import domain.model.Employee;
import domain.port.repository.EmployeeRepository;

public class MentoringService {
    private final EmployeeRepository employeeRepository;

    public MentoringService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void addMentor(Employee employee, Employee mentor) {
        if (isNotAMember(employee))
            throw new EmployeeDoesNotExistsException();
        if (isNotAMember(mentor))
            throw new MentorDoesNotExistException();

        employee.addMentor(mentor);
        employeeRepository.update(employee, employee);
    }

    public Employee getMentorOf(Employee employee) {
        Employee requestedEmployee = employeeRepository.get(employee);
        return requestedEmployee.mentor();
    }

    private boolean isNotAMember(Employee employee) {
        return employeeRepository.get(employee) == null;
    }
}
