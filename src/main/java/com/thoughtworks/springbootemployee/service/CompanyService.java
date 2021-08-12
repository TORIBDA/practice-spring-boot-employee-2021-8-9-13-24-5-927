package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.CouldNotFindCompanyException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company findCompanyById(Integer companyID) {
        return companyRepository.findById(companyID).orElseThrow(CouldNotFindCompanyException::new);
    }

    public List<Employee> getAllEmployees(Integer companyID) {
        return findCompanyById(companyID).getEmployees();
    }
//
//    public List<Company> getCompaniesByPage(int pageIndex, int pageSize) {
//        int skipValue = (pageIndex - 1) * pageSize;
//        return getAllCompanies().stream()
//                .skip(skipValue)
//                .limit(pageSize)
//                .collect(Collectors.toList());
//    }
//
//    public void addCompany(Company company) {
//        Company companyToBeAdded = new Company(retiredCompanyRepository.getCompanies().size() + 1, company.getName());
//        getAllCompanies().add(companyToBeAdded);
//    }
//
//    public void updateCompany(Integer companyID, Company companyNewInformation) {
//        updateCompanyInfo(findCompanyById(companyID), companyNewInformation);
//    }
//
//    private void updateCompanyInfo(Company companyToBeUpdated, Company companyNewInformation) {
//        if (companyNewInformation.getName() != null) {
//            companyToBeUpdated.setName(companyNewInformation.getName());
//        }
//        if (companyNewInformation.getEmployeeRepository() != null) {
//            companyToBeUpdated.setEmployeeRepository(companyNewInformation.getEmployeeRepository());
//        }
//    }
//
//    public void deleteCompany(Integer companyID) {
//        getAllCompanies().stream()
//                .filter(company -> company.getId().equals(companyID))
//                .findFirst()
//                .ifPresent(companyToRemove -> getAllCompanies().remove(companyToRemove));
//    }
}
