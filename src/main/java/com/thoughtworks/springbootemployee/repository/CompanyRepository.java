package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Company;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CompanyRepository {

    private List<Company> companies = new ArrayList<>();

    public CompanyRepository() {
        companies.add(new Company(1,"DebidsCompany"));
        companies.add(new Company(2,"Debids2Company"));
        companies.add(new Company(3,"Debids3Company"));
    }

    public List<Company> getCompanies() {
        return companies;
    }
}
