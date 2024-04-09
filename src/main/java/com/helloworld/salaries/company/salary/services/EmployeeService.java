package com.helloworld.salaries.company.salary.services;

import com.helloworld.salaries.exceptions.NoDataFoundException;
import com.helloworld.salaries.exceptions.WrongParamsException;

public interface EmployeeService {
    public double getSalaryInYear(String employeeCode, int year) throws NoDataFoundException;
}
