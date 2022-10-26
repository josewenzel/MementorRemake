package domain.service;

import domain.exception.SelfMentorException;
import domain.model.Employee;
import domain.port.repository.EmployeeRepository;
import domain.validator.EmployeeExistenceValidator;

public class MentoringService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeExistenceValidator employeeExistenceValidator;

    public MentoringService(EmployeeRepository employeeRepository, EmployeeExistenceValidator employeeExistenceValidator) {
        this.employeeRepository = employeeRepository;
        this.employeeExistenceValidator = employeeExistenceValidator;
    }

    public void addMentor(Employee employee, Employee mentor) {
        employeeExistenceValidator.validate(employee);
        employeeExistenceValidator.validate(mentor);
        if (employee == mentor) throw new SelfMentorException();

        employee.addMentor(mentor);
        employeeRepository.update(employee.id(), employee);
    }

    public Employee getMentorOf(Employee employee) {
        Employee requestedEmployee = employeeRepository.get(employee);
        return requestedEmployee.mentor();
    }

    public void removeMentor(Employee employee) {
        employeeExistenceValidator.validate(employee);

        employee.removeMentor();
        employeeRepository.update(employee.id(), employee);
    }
}
