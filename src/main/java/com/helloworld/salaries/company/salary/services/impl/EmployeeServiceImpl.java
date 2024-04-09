package com.helloworld.salaries.company.salary.services.impl;

import com.helloworld.salaries.company.salary.mapper.EmployeeMapper;
import com.helloworld.salaries.company.salary.services.EmployeeService;
import com.helloworld.salaries.exceptions.NoDataFoundException;
import com.helloworld.salaries.exceptions.WrongParamsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;

    @Override
    public double getSalaryInYear(String employeeCode, int year) throws NoDataFoundException {
        Optional<Double> salary = employeeMapper.salaryOnYear(employeeCode, year);
        if (salary.isPresent()) {
            return salary.get();
        }
        throw new NoDataFoundException(String.format("Salario para el empleado %s en el año %d", employeeCode, year));
    }
}
