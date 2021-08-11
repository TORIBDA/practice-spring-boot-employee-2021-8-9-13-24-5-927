package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Resource
    private EmployeeRepository employeeRepository;

    public EmployeeService() {
    }

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getEmployees();
    }

    public Employee findById(Integer employeeId) {
        return employeeRepository.getEmployees().stream()
                .filter(employee -> employee.getId().equals(employeeId))
                .findFirst()
                .orElse(null);
    }

    public List<Employee> getEmployeesByGender(String gender) {
        return employeeRepository.getEmployees().stream()
                .filter(employee -> employee.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    public List<Employee> getEmployeesByPage(Integer page, Integer pageSize) {
        int skipValue = (page - 1) * pageSize;
        return employeeRepository.getEmployees().stream()
                .skip(skipValue)
                .limit(pageSize)
                .collect(Collectors.toList());
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
