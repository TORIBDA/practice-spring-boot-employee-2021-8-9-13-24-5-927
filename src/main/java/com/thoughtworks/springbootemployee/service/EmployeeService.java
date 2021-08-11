package com.thoughtworks.springbootemployee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmployeeService {
    @Resource
    private EmployeeRepository employeeRepository;

    public EmployeeService() {}

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getEmployees();
    }
    public void addEmployee(@RequestBody Employee employee) {
        Employee employeeToBeAdded = new Employee(employeeRepository.getEmployees().size() + 1,
                employee.getName(),
                employee.getAge(),
                employee.getGender(),
                employee.getSalary());
        employeeRepository.getEmployees().add(employeeToBeAdded);
    }
}
