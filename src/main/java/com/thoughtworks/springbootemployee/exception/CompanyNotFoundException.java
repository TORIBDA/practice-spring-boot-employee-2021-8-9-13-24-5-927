package com.thoughtworks.springbootemployee.exception;

public class CompanyNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return "The company could not be found";
    }
}
