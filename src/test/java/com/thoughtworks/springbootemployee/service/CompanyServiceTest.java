package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
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

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {
    @InjectMocks
    private CompanyService companyService;

    @Mock
    private CompanyRepository companyRepository;

    @Test
    public void should_return_all_companies_when_get_all_companies_given_all_companies() {
        //Given
        List<Company> companies = new ArrayList<>();
        companies.add(new Company(1,"Debidss"));
        companies.add(new Company(2,"Bobby"));
        companies.add(new Company(3,"Mario"));
        Mockito.when(companyRepository.getCompanies()).thenReturn(companies);

        //When
        List<Company> actualCompanies = companyService.getAllCompanies();

        //Then
        assertEquals(companies, actualCompanies);
    }

    @Test
    public void should_return_company_when_get_company_by_id_given_existing_companies() {
        //Given
        List<Company> companies = new ArrayList<>();
        Company expectedCompany = new Company(1,"Debidss");
        companies.add(expectedCompany);
        Mockito.when(companyRepository.getCompanies()).thenReturn(companies);

        //When
        Company actualCompany = companyService.findCompanyById(1);

        //Then
        assertEquals(expectedCompany, actualCompany);
    }

    @Test
    public void should_return_all_employees_when_get_all_employees_of_company_given_specific_company() {
        //Given
        List<Company> companies = new ArrayList<>();
        Company expectedCompany = new Company(1,"Debidss");
        companies.add(expectedCompany);
        Mockito.when(companyRepository.getCompanies()).thenReturn(companies);

        //When
        List<Employee> actualCompany = companyService.getAllEmployees(1);

        //Then
        assertEquals(expectedCompany.getEmployeeRepository().getEmployees(), actualCompany);
    }

    @Test
    public void should_return_employees_by_page_when_find_employee_by_page_given_page_index_page_size() {
        //Given
        List<Company> companies = new ArrayList<>();
        companies.add(new Company(1,"Debidss"));
        companies.add(new Company(2,"D1"));
        companies.add(new Company(3,"D2"));
        companies.add(new Company(4,"D3"));
        companies.add(new Company(5,"D4"));
        companies.add(new Company(6,"D5"));
        companies.add(new Company(7,"D6"));
        companies.add(new Company(8,"D7"));
        int pageIndex = 2;
        int pageSize = 5;
        List<Company> expectedCompanies =  companies.stream()
                .skip((pageIndex - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
        Mockito.when(companyRepository.getCompanies()).thenReturn(companies);

        //When
        List<Company> actualCompanies = companyService.getCompaniesByPage(pageIndex, pageSize);

        //Then
        assertEquals(expectedCompanies, actualCompanies);
    }
}
