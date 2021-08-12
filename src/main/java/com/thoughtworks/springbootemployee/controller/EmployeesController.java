package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(path = "/{employeeId}")
    public Employee findById(@PathVariable Integer employeeId) {
        return employeeService.findById(employeeId);
    }

    @GetMapping(params = {"page", "pagesize"})
    public List<Employee> getEmployeesByPage(@RequestParam(name = "page", required = true) Integer page, @RequestParam(name = "pagesize", required = true) Integer pageSize) {
        return employeeService.getEmployeesByPage(page, pageSize);
    }

    @GetMapping(params = {"gender"})
    public List<Employee> getEmployeesByGender(@RequestParam(name = "gender", required = true) String gender) {
        return employeeService.getEmployeesByGender(gender);
    }

    @PostMapping
    public void addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
    }

    @PutMapping(path = "/{employeeId}")
    public void updateEmployee(@PathVariable Integer employeeId, @RequestBody Employee employeeToBeUpdated) {
        employeeService.updateEmployee(employeeId, employeeToBeUpdated);
    }

    @DeleteMapping(path = "/{employeeId}")
    public void deleteEmployee(@PathVariable Integer employeeId) {
        employeeService.deleteEmployee(employeeId);
    }
}