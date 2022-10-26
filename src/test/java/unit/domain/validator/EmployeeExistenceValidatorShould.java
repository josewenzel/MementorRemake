package unit.domain.validator;

import domain.exception.EmployeeDoesNotExistsException;
import domain.model.Employee;
import domain.port.repository.EmployeeRepository;
import domain.validator.EmployeeExistenceValidator;
import fixture.EmployeeFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeExistenceValidatorShould {
    @Mock
    EmployeeRepository employeeRepository;
    private final Employee anEmployee = new EmployeeFixture().build();

    @Test
    public void does_not_raise_an_error_if_employee_exists() {
        EmployeeExistenceValidator validator = new EmployeeExistenceValidator(employeeRepository);
        when(employeeRepository.get(anEmployee)).thenReturn(anEmployee);

        assertThatNoException().isThrownBy(() -> validator.validate(anEmployee));
    }

    @Test
    public void raises_an_error_if_employee_does_not_exist() {
        EmployeeExistenceValidator validator = new EmployeeExistenceValidator(employeeRepository);
        when(employeeRepository.get(anEmployee)).thenReturn(null);

        assertThatThrownBy(() -> validator.validate(anEmployee))
                .isInstanceOf(EmployeeDoesNotExistsException.class);
    }
}
