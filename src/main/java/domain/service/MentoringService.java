package domain.service;

import domain.model.Employee;
import domain.port.repository.EmployeeRepository;

public class MentoringService {
    private EmployeeRepository employeeRepository;

    public MentoringService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void addMentor(Employee employee, Employee mentor) {
        throw new UnsupportedOperationException("Not Implemented");
    }

    public Employee getMentorOf(Employee employee) {
        throw new UnsupportedOperationException("Not Implemented");
    }
}
