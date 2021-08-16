package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.model.EmployeeRequest;
import com.thoughtworks.springbootemployee.model.EmployeeResponse;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeMapper employeeMapper;

    @GetMapping
    public List<EmployeeResponse> getAllEmployees() {
        return employeeMapper.toResponse(employeeService.getAllEmployees());
    }

    @GetMapping(path = "/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeResponse findById(@PathVariable Integer employeeId) {
        return employeeMapper.toResponse(employeeService.findById(employeeId));
    }

    @GetMapping(params = {"gender"})
    public List<EmployeeResponse> getEmployeesByGender(@RequestParam(name = "gender", required = true) String gender) {
        return employeeMapper.toResponse(employeeService.getEmployeesByGender(gender));
    }

    @GetMapping(params = {"pageIndex", "pageSize"})
    public List<EmployeeResponse> getEmployeesByPage(@RequestParam(name = "pageIndex", required = true) Integer page,
                                                     @RequestParam(name = "pageSize", required = true) Integer pageSize) {
        return employeeMapper.toResponse(employeeService.getEmployeesByPage(page, pageSize));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse addEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return employeeMapper.toResponse(employeeService.addEmployee(employeeMapper.toEntity(employeeRequest)));
    }

    @PutMapping(path = "/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeResponse updateEmployee(@PathVariable Integer employeeId, @RequestBody EmployeeRequest employeeRequest) {
        return employeeMapper.toResponse(employeeService.updateEmployee(employeeId,
                employeeMapper.toEntity(employeeRequest)));
    }

    @DeleteMapping(path = "/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable Integer employeeId) {
        employeeService.deleteEmployee(employeeId);
    }
}