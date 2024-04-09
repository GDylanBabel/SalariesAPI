package com.helloworld.salaries.company.salary.services.impl;

import com.helloworld.salaries.company.salary.mapper.SalaryMapper;
import com.helloworld.salaries.company.salary.model.Salary;
import com.helloworld.salaries.company.salary.services.AvgSalaryService;
import com.helloworld.salaries.exceptions.NoDataFoundException;
import com.helloworld.salaries.exceptions.WrongParamsException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvgSalaryServiceImpl implements AvgSalaryService {

    private final int minimalYear = 2000;

    private final SalaryMapper mapper;

    @Override
    public double getAvgSalary(int year) throws WrongParamsException, NoDataFoundException {
        validateYear(year);
        Optional<Double> salary = mapper.avgSalary(year);
        if (salary.isPresent()) {
            return salary.get();
        }
        throw new NoDataFoundException("Salary in year " + year);
    }

    private void validateYear(int year) throws WrongParamsException {
        if (year < this.minimalYear || year > LocalDate.now().getYear()) {
            throw new WrongParamsException("year");
        }
    }


}
