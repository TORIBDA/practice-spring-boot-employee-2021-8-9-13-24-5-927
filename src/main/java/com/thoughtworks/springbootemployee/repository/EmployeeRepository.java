package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    private List<Employee> employees = new ArrayList<>();

    public EmployeeRepository() {
        employees.add(new Employee(1, "Joanna", 23, "female", 1000));
        employees.add(new Employee(2, "David", 23, "male", 1500));
        employees.add(new Employee(3, "David", 23, "male", 1500));
        employees.add(new Employee(4, "David", 23, "male", 1500));
        employees.add(new Employee(5, "David", 23, "male", 1500));
        employees.add(new Employee(6, "Joanna", 18, "female", 1500));
        employees.add(new Employee(7, "David", 23, "male", 1500));
        employees.add(new Employee(8, "David", 23, "male", 1500));
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
