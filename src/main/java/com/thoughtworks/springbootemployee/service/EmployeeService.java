package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
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
        return employeeRepository.findById(employeeId).orElse(null);
    }

    public List<Employee> getEmployeesByGender(String gender) {
        return employeeRepository.findAllByGender(gender);
    }
//
//    public List<Employee> getEmployeesByPage(Integer page, Integer pageSize) {
//        int skipValue = (page - 1) * pageSize;
//        return getAllEmployees().stream()
//                .skip(skipValue)
//                .limit(pageSize)
//                .collect(Collectors.toList());
//    }
//
//    public void addEmployee(@RequestBody Employee employee) {
//        Employee employeeToBeAdded = new Employee(retiredEmployeeRepository.getEmployees().size() + 1,
//                employee.getName(),
//                employee.getAge(),
//                employee.getGender(),
//                employee.getSalary());
//        getAllEmployees().add(employeeToBeAdded);
//    }
//
//    public void updateEmployee(Integer employeeId, Employee employeeToBeUpdated) {//TODO: by id
//        //updateEmployeeInfo(findById(employeeId), employeeToBeUpdated);
//        retiredEmployeeRepository.getEmployees().stream()
//                .filter(employee -> employee.getId().equals(employeeId))
//                .findFirst()
//                .map(employee -> updateEmployeeInfo(employee, employeeToBeUpdated));
//    }
//
//    private Employee updateEmployeeInfo(Employee employee, Employee employeeToBeUpdated) {
//        if (employeeToBeUpdated.getName() != null) {
//            employee.setName(employeeToBeUpdated.getName());
//        }
//        if (employeeToBeUpdated.getAge() != null) {
//            employee.setAge(employeeToBeUpdated.getAge());
//        }
//        if (employeeToBeUpdated.getGender() != null) {
//            employee.setGender(employeeToBeUpdated.getGender());
//        }
//        if (employeeToBeUpdated.getSalary() != null) {
//            employee.setSalary(employeeToBeUpdated.getSalary());
//        }
//        return employee;
//    }
//
//    public void deleteEmployee(Integer employeeID) {
//        getAllEmployees().stream()
//                .filter(employee -> employee.getId().equals(employeeID))
//                .findFirst()
//                .ifPresent(employeeToRemove -> retiredEmployeeRepository.getEmployees().remove(employeeToRemove));
//    }
}
