package com.thoughtworks.springbootemployee.model;

import com.thoughtworks.springbootemployee.repository.EmployeeRepository;

import java.util.List;
import java.util.Objects;

public class Company {
    private final int id;
    private final String name;
    private EmployeeRepository employeeRepository = new EmployeeRepository();

    public Company(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Company(List<Employee> employees, int id, String name) {
        this.id = id;
        this.name = name;
    }

    public EmployeeRepository getEmployeeRepository() {
        return employeeRepository;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return id == company.id && Objects.equals(name, company.name) && Objects.equals(employeeRepository, company.employeeRepository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, employeeRepository);
    }
}
