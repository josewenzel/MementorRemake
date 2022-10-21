package domain.service;

import domain.exception.MentorDoesNotExistException;
import domain.model.Employee;
import domain.port.repository.EmployeeRepository;

public class MentoringService {
    private EmployeeRepository employeeRepository;

    public MentoringService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void addMentor(Employee employee, Employee mentor) {
        if (isNotAMember(mentor))
            throw new MentorDoesNotExistException();
        employee.addMentor(mentor);
        employeeRepository.update(employee, employee);
    }

    private boolean isNotAMember(Employee mentor) {
        return employeeRepository.get(mentor) == null;
    }

    public Employee getMentorOf(Employee employee) {
        Employee requestedEmployee = employeeRepository.get(employee);
        return requestedEmployee.mentor();
    }
}
