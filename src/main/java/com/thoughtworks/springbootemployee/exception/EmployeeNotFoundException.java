package com.thoughtworks.springbootemployee.exception;

public class EmployeeNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return "The employee could not be found";
    }
}
