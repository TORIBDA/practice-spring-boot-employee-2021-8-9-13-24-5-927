package com.thoughtworks.springbootemployee.model;

import com.thoughtworks.springbootemployee.entity.Employee;

import java.util.LinkedList;
import java.util.List;

public class CompanyRequest {
    private String company_name;
    private List<Employee> employees = new LinkedList<>();

    public CompanyRequest() {}

    public CompanyRequest(String name) {
        this.company_name = name;
    }

    public CompanyRequest(String name, List<Employee> employees) {
        this.company_name = name;
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
