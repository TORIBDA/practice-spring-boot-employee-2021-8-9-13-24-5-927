package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
    public void should_return_employee_when_find_employee_given_employee_id() {
        //Given
        List<Employee> employees = new ArrayList<>();
        Employee expectedEmployee = new Employee(employeeService.getAllEmployees().size() + 1,
                "Joanna",
                23,
                "female",
                1000);
        Mockito.when(employeeRepository.getEmployees()).thenReturn(employees);

        //When
        employeeService.addEmployee(expectedEmployee);
        Employee actualEmployee = employeeService.findById(expectedEmployee.getId());

        //Then
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    public void should_return_employees_when_find_employee_given_employee_gender() {
        //Given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(employeeService.getAllEmployees().size() + 1, "Joanna", 23, "female", 1000));
        employees.add(new Employee(employeeService.getAllEmployees().size() + 1, "Deb", 23, "male", 1000));
        employees.add(new Employee(employeeService.getAllEmployees().size() + 1, "Joanna", 23, "female", 1000));
        employees.add(new Employee(employeeService.getAllEmployees().size() + 1, "Debi", 23, "male", 1000));
        employees.add(new Employee(employeeService.getAllEmployees().size() + 1, "Joanna", 23, "female", 1000));
        employees.add(new Employee(employeeService.getAllEmployees().size() + 1, "Debids", 23, "male", 1000));
        List<Employee> expectedEmployees = employees.stream()
                .filter(employee -> employee.getGender().equals("male"))
                .collect(Collectors.toList());
        Mockito.when(employeeRepository.getEmployees()).thenReturn(employees);

        //When
        List<Employee> actualEmployees = employeeService.getEmployeesByGender("male");

        //Then
        assertEquals(expectedEmployees, actualEmployees);
    }

    @Test
    public void should_return_employees_by_page_when_find_employee_by_page_given_page_index_page_size() {
        //Given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Joanna", 23, "female", 1000));
        employees.add(new Employee(2, "Deb", 23, "male", 1000));
        employees.add(new Employee(3, "Joanna", 23, "female", 1000));
        employees.add(new Employee(4, "Debi", 23, "male", 1000));
        employees.add(new Employee(5, "Joanna", 23, "female", 1000));
        employees.add(new Employee(6, "Debids", 23, "male", 1000));
        employees.add(new Employee(7, "Debids", 23, "male", 1000));
        int pageIndex = 2;
        int pageSize = 5;
        List<Employee> expectedEmployees =  employees.stream()
                .skip((pageIndex - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
        Mockito.when(employeeRepository.getEmployees()).thenReturn(employees);

        //When
        List<Employee> actualEmployees = employeeService.getEmployeesByPage(pageIndex, pageSize);

        //Then
        assertEquals(expectedEmployees, actualEmployees);
    }

    @Test
    public void should_add_employee_when_added_employee_given_employees() {
        //Given
        List<Employee> employees = new ArrayList<>();
        Employee expectedEmployee = new Employee(employeeService.getAllEmployees().size() + 1,
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

    @Test
    public void should_update_employee_when_update_employee_given_existing_employee() {
        //Given
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee(1,"Joanna",23,"female",1000);
        employees.add(employee);
        Mockito.when(employeeRepository.getEmployees()).thenReturn(employees);
        //When
        Employee expectedEmployee = new Employee(1,"Dibed",18,"male",10000);
        employeeService.updateEmployee(expectedEmployee.getId(), expectedEmployee);
        Employee actualEmployee = employeeService.getAllEmployees().stream()
                .filter(employeeValue -> employeeValue.getId().equals(expectedEmployee.getId()))
                .findFirst()
                .orElse(null);
        //Then
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    public void should_delete_employee_when_delete_employee_given_existing_employee() {
        //Given
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee(1,"Joanna",23,"female",1000);
        employees.add(employee);
        Mockito.when(employeeRepository.getEmployees()).thenReturn(employees);
        //When
        employeeService.deleteEmployee(employee.getId());
        Employee actualEmployee = employeeService.getAllEmployees().stream()
                .filter(employeeValue -> employeeValue.getId().equals(employee.getId()))
                .findFirst()
                .orElse(null);
        //Then
        assertNull(actualEmployee);
    }
}
