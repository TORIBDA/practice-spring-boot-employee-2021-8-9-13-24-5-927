package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
        return employeeRepository.findById(employeeId).orElse(null);
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
        return employee;
    }
//
//    public void deleteEmployee(Integer employeeID) {
//        getAllEmployees().stream()
//                .filter(employee -> employee.getId().equals(employeeID))
//                .findFirst()
//                .ifPresent(employeeToRemove -> retiredEmployeeRepository.getEmployees().remove(employeeToRemove));
//    }
}
