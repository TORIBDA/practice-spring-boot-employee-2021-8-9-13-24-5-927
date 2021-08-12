package com.thoughtworks.springbootemployee.model;

import com.thoughtworks.springbootemployee.repository.EmployeeRepository;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String company_name;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "company_id")
    private List<Employee> employees = new LinkedList<>();

    public Company() {}

    public Company(int id, String name) {
        this.id = id;
        this.company_name = name;
        this.employees = employees;
    }

    public Company(int id, String name, List<Employee> employees) {
        this.id = id;
        this.company_name = name;
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Integer getId() {
        return id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return id == company.id && Objects.equals(company_name, company.company_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, company_name);
    }
}
