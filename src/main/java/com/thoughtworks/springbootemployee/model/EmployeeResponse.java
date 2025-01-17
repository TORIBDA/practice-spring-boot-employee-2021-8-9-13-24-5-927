package com.thoughtworks.springbootemployee.model;

public class EmployeeResponse {

    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private Integer salary;
    private Integer company_id;


    public EmployeeResponse() {
    }

    public EmployeeResponse(String name, Integer age, String gender, Integer salary) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }

    public EmployeeResponse(String name, Integer age, String gender, Integer salary, Integer company_id) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.company_id = company_id;
    }

    public EmployeeResponse(Integer id, String name, Integer age, String gender, Integer salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }

    public EmployeeResponse(Integer id, String name, Integer age, String gender, Integer salary, Integer company_id) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.company_id = company_id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public Integer getSalary() {
        return salary;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }
}
