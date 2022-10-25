package unit.domain.validator;

import domain.exception.DuplicateEmployeeException;
import domain.model.Employee;
import domain.port.repository.EmployeeRepository;
import domain.validator.DuplicatedEmployeeValidator;
import fixture.EmployeeFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DuplicatedEmployeeValidatorShould {
    private final Employee anEmployee = new EmployeeFixture().build();
    @Mock
    EmployeeRepository employeeRepository;

    @Test
    public void does_not_raise_an_error_if_employee_is_new() {
        DuplicatedEmployeeValidator validator = new DuplicatedEmployeeValidator(employeeRepository);
        when(employeeRepository.get(anEmployee)).thenReturn(null);

        assertThatNoException().isThrownBy(() -> validator.validate(anEmployee));
    }

    @Test
    public void raises_an_error_if_employee_is_duplicated() {
        DuplicatedEmployeeValidator validator = new DuplicatedEmployeeValidator(employeeRepository);
        when(employeeRepository.get(anEmployee)).thenReturn(anEmployee);

        assertThatThrownBy(() -> validator.validate(anEmployee))
                .isInstanceOf(DuplicateEmployeeException.class);
    }
}
