package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @InjectMocks
    private EmployeeService employeeService;
    
    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void should_return_all_employees_when_getAllEmployees_given_all_employees() {
        //Given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Joanna", 23, "female", 1000));
        employees.add(new Employee(2, "David", 18, "male", 1000));
        employees.add(new Employee(3, "Bobby", 18, "male", 1000));
        Mockito.when(employeeRepository.getEmployees()).thenReturn(employees);

        //When
        List<Employee> actualEmployees = employeeService.getAllEmployees();

        //Then
        assertEquals(employees, actualEmployees);
    }

    @Test
    public void should_add_employee_when_added_employee_given_employees() {
        //Given
        List<Employee> employees = new ArrayList<>();
        Employee expectedEmployee = new Employee(employeeService.getAllEmployees().size()+1,
                "Joanna",
                23,
                "female",
                1000);
        Mockito.when(employeeRepository.getEmployees()).thenReturn(employees);

        //When
        employeeService.addEmployee(expectedEmployee);
        List<Employee> actualEmployee = employeeService.getAllEmployees();

        //Then
        assertEquals(expectedEmployee, actualEmployee.get(0));
    }
}
