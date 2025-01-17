package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee findById(Integer employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> getEmployeesByGender(String gender) {
        return employeeRepository.findAllByGender(gender);
    }

    public List<Employee> getEmployeesByPage(Integer pageIndex, Integer pageSize) {
        return employeeRepository.findAll(PageRequest.of(pageIndex-1,pageSize)).getContent();
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Integer employeeId, Employee updatedEmployeeInformation) {
        Employee updatedEmployee = updateEmployeeInfo(findById(employeeId), updatedEmployeeInformation);
        return employeeRepository.save(updatedEmployee);
    }

    private Employee updateEmployeeInfo(Employee employee, Employee employeeToBeUpdated) {
        if (employeeToBeUpdated.getName() != null) {
            employee.setName(employeeToBeUpdated.getName());
        }
        if (employeeToBeUpdated.getAge() != null) {
            employee.setAge(employeeToBeUpdated.getAge());
        }
        if (employeeToBeUpdated.getGender() != null) {
            employee.setGender(employeeToBeUpdated.getGender());
        }
        if (employeeToBeUpdated.getSalary() != null) {
            employee.setSalary(employeeToBeUpdated.getSalary());
        }
        if (employeeToBeUpdated.getCompany_id() != null) {
            employee.setCompany_id(employeeToBeUpdated.getCompany_id());
        }
        return employee;
    }

    public void deleteEmployee(Integer employeeID) {
        Employee employeeToRemove = findById(employeeID);
        employeeRepository.delete(employeeToRemove);
    }
}
