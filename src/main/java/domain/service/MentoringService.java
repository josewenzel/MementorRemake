package domain.service;

import domain.model.Employee;
import domain.port.repository.EmployeeRepository;

public class MentoringService {
    private EmployeeRepository employeeRepository;

    public MentoringService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void addMentor(Employee employee, Employee mentor) {
        employee.addMentor(mentor);
        employeeRepository.update(employee, employee);
    }

    public Employee getMentorOf(Employee employee) {
        Employee requestedEmployee = employeeRepository.get(employee);
        return requestedEmployee.mentor();
    }
}
