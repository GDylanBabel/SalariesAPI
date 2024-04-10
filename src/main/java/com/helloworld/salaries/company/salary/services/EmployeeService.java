package com.helloworld.salaries.company.salary.services;

import com.helloworld.salaries.company.salary.model.Employee;
import com.helloworld.salaries.company.salary.model.dto.EmployeeDTO;
import com.helloworld.salaries.exceptions.NoDataFoundException;
import com.helloworld.salaries.exceptions.WrongParamsException;
import jakarta.validation.constraints.Digits;

import java.util.List;

public interface EmployeeService {
    public double getSalaryInYear(String employeeCode, int year) throws NoDataFoundException;

    public void putEmployeeSalaryInMonth(String employeeCode, int month, int year, double salary);

    List<EmployeeDTO> getEmployee(String name, String employeeCode, int page);
}
