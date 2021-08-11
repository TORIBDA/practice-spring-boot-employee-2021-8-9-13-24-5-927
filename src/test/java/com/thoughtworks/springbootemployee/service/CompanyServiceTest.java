package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

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
}
