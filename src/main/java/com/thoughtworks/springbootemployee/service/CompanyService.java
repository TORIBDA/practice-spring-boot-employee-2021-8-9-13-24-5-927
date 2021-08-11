package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    @Resource
    private CompanyRepository companyRepository;

    public CompanyService() {
    }

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.getCompanies();
    }

    public Company findCompanyById(Integer companyID) {
        return companyRepository.getCompanies().stream()
                .filter(company -> company.getId() == companyID)
                .findFirst()
                .orElse(null);
    }

    public List<Employee> getAllEmployees(Integer companyID) {
        return Objects.requireNonNull(companyRepository.getCompanies().stream()
                .filter(company -> company.getId() == companyID)
                .findFirst()
                .orElse(null))
                .getEmployeeRepository()
                .getEmployees();
    }

    public List<Company> getCompaniesByPage(int pageIndex, int pageSize) {
        int skipValue = (pageIndex - 1) * pageSize;
        return companyRepository.getCompanies().stream()
                .skip(skipValue)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public void addCompany(Company company) {
        Company companyToBeAdded = new Company(companyRepository.getCompanies().size() + 1, company.getName());
        companyRepository.getCompanies().add(companyToBeAdded);
    }

    public void updateCompany(Integer companyID, Company companyNewInformation) {
        companyRepository.getCompanies().stream()
                .filter(company -> company.getId().equals(companyID))
                .findFirst()
                .map(company -> updateCompanyInfo(company, companyNewInformation));
    }

    private Company updateCompanyInfo(Company companyToBeUpdated, Company companyNewInformation) {
        if (companyNewInformation.getName() != null) {
            companyToBeUpdated.setName(companyNewInformation.getName());
        }
        if (companyNewInformation.getEmployeeRepository() != null) {
            companyToBeUpdated.setEmployeeRepository(companyNewInformation.getEmployeeRepository());
        }
        return companyToBeUpdated;
    }

    public void deleteCompany(Integer companyID) {
        companyRepository.getCompanies().stream()
                .filter(company -> company.getId().equals(companyID))
                .findFirst()
                .ifPresent(companyToRemove -> companyRepository.getCompanies().remove(companyToRemove));
    }
}
