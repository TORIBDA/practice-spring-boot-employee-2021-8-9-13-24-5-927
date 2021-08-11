package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    private List<Company> companies = new ArrayList<>();

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping(path = "/{companyID}")
    public Company findCompanyByID(@PathVariable Integer companyID) {
        return companyService.findCompanyById(companyID);
    }

    @GetMapping(path = "/{companyID}/employees")
    public List<Employee> getAllEmployees(@PathVariable Integer companyID) {
        return companyService.getAllEmployees(companyID);
    }

    @GetMapping(params = {"page", "pagesize"})
    public List<Company> getCompaniesByPage(@RequestParam(name = "page", required = true) Integer page, @RequestParam(name = "pagesize", required = true) Integer pageSize) {
        return companyService.getCompaniesByPage(page, pageSize);
    }
}
