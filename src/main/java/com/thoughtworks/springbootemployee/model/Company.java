package com.thoughtworks.springbootemployee.model;

import com.thoughtworks.springbootemployee.repository.EmployeeRepository;

import java.util.List;
import java.util.Objects;

public class Company {

    private Integer id;
    private String name;
    private EmployeeRepository employeeRepository = new EmployeeRepository();

    public Company() {}

    public Company(int id, String name) {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return id == company.id && Objects.equals(name, company.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
