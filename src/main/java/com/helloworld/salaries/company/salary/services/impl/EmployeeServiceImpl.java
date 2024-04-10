package com.helloworld.salaries.company.salary.services.impl;

import com.helloworld.salaries.company.salary.mapper.EmployeeMapper;
import com.helloworld.salaries.company.salary.mapper.SalaryMapper;
import com.helloworld.salaries.company.salary.model.Employee;
import com.helloworld.salaries.company.salary.model.dto.EmployeeDTO;
import com.helloworld.salaries.company.salary.services.EmployeeService;
import com.helloworld.salaries.exceptions.NoDataFoundException;
import com.helloworld.salaries.exceptions.WrongParamsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final SalaryMapper salaryMapper;
    private final EmployeeMapper employeeMapper;

    @Override
    public double getSalaryInYear(String employeeCode, int year) throws NoDataFoundException {
        Optional<Double> salary = salaryMapper.salaryOnYear(employeeCode, year);
        if (salary.isPresent()) {
            return salary.get();
        }
        throw new NoDataFoundException(String.format("Salario para el empleado %s en el año %d", employeeCode, year));
    }

    @Override
    public void putEmployeeSalaryInMonth(String employeeCode, int month, int year, double salary) {
        salaryMapper.putEmployeeSalaryInMonth(employeeCode, month, year, salary);
    }

    @Override
    public List<EmployeeDTO> getEmployee(String name, String employeeCode, int page) {
        if (name == null || name.isBlank()) {
            name = "";
        }
        if (employeeCode == null || employeeCode.isBlank()) {
            employeeCode = "";
        }
        return employeeMapper.getEmployee( name + "%", "%" + employeeCode , page == 1 ? 0 : (page - 1) * 10, 10);
    }


}
